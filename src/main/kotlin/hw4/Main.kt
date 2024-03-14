package hw4

import java.io.File
import kotlin.io.path.Path

val PHONE_REGEX = Regex("""add [A-Za-z0-9._-]+ phone \+?[0-9]+""")
val EMAIL_REGEX = Regex("""add [A-Za-z0-9._-]+ email [a-z0-9._-]+@[a-z0-9._-]+.[a-z]+""")
val SHOW_REGEX = Regex("""show [A-Za-z0-9._-]+""")
val FIND_REGEX = Regex("""find \+?[A-Za-z0-9@._-]+""")
val EXPORT_REGEX = Regex("""export [A-Za-z0-9/._-]+.[a-z0-9]+""")


val dictionary = mutableMapOf<String, Info>()


//1. Измените класс Person так, чтобы он содержал список телефонов и список
//почтовых адресов, связанных с человеком.
data class Person(
    val name: String,
    val info: Info
)

data class Info(
    val phones: List<String>,
    val emails: List<String>
)

sealed interface Command {
    fun isValid(): Boolean
}

class Help(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText == "help"
    }
    fun printHelp(){
        println("exit")
        println("help")
        println("add <Имя> phone <Номер телефона>")
        println("add <Имя> email <Адрес электронной почты>")
        println("show <Имя>")
        println("find <параметр>")
    }
}

class Exit(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText == "exit"
    }
}

class AddEmail(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText.matches(EMAIL_REGEX)
    }

    fun addEmail(){
        val parts = fullCommandText.split(" ")
        val name = parts[1]
        val email = parts[3]

        val info = dictionary[name]
        if(info == null){
            dictionary[name] = Info(mutableListOf(), mutableListOf(email))
        }else{
            info.emails.addLast(email)
        }
        println("Email добавлен")
    }

    fun getText(): String {
        return fullCommandText
    }
}

class AddPhone(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText.matches(PHONE_REGEX)
    }


    fun addPhone(){
        val parts = fullCommandText.split(" ")
        val name = parts[1]
        val phone = parts[3]

        val info = dictionary[name]
        if(info == null){
            dictionary[name] = Info(mutableListOf(phone), mutableListOf())
        }else{
            info.phones.addLast(phone)
        }
        println("Phone добавлен")
    }

    fun getText(): String {
        return fullCommandText
    }
}

class Show(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText.matches(SHOW_REGEX)
    }


    fun showPerson(){
        val person = findPerson()

        if(person != null) println(person)
        else println("-- Не найден!")
    }

    fun findPerson(): Person?{
        val parts = fullCommandText.split(" ")
        val name = parts[1]
        val info = dictionary[name]

        return if(info != null)
            Person(name, info)
        else
            null
    }
}

class Unknown() : Command {
    override fun isValid(): Boolean {
        return true
    }

    fun showError(){
        println("  -- Комманда не распознана! Повторите.")
        Help("").printHelp()
    }
}

class Find(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText.matches(FIND_REGEX)
    }

    fun findAndShow(){
        val persons = find()
        if(persons.isNotEmpty()) println(persons)
        else println("-- Совпадений не найдено")
    }

    fun find(): Set<Person>{
        val found = fullCommandText.split(" ")[1]
        val persons = mutableSetOf<Person>()
        dictionary.forEach{(key, value) ->
            val phones = value.phones
            val emails = value.emails
            if (phones.indexOf(found) >= 0) persons.add(Person(key, value))
            if (emails.indexOf(found) >= 0) persons.add(Person(key, value))
        }
        return persons
    }
}

class Export(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText.matches(EXPORT_REGEX)
    }

    fun export(){
        val fileName = fullCommandText.substringAfter("export ")

        val jsonDict = json{
            for (key in dictionary.keys){
                objectElement(key){
                    val info = dictionary[key]
                    arrayElement("phones") {
                        info?.phones?.forEach {
                            stringElement(it)
                        }
                    }
                    arrayElement("emails") {
                        info?.emails?.forEach {
                            stringElement(it)
                        }
                    }
                }
            }
        }

        //println(jsonDict)
        try {
            File(fileName).writeText(jsonDict)
        } catch (exception: Exception){
            val msg = "Error to write file $fileName!\n" + exception.message
            throw Exception(msg)
        }

        println("Export Successful")
    }

}

fun main() {
    while (true) {
        // a. Читаем команду с помощью функции readCommand
        val command = readCommand()
        // b. Выводим на экран получившийся экземпляр Command
//        println(command)
        // c. Если isValid для команды возвращает false, выводим help.
        //    Если true, обрабатываем команду внутри when.
        if (command.isValid()) {
            when (command) {
                is Exit -> return
                is AddEmail -> command.addEmail()
                is AddPhone -> command.addPhone()
                is Help -> command.printHelp()
                is Show -> command.showPerson()
                is Find -> command.findAndShow()
                is Export -> command.export()
                is Unknown -> command.showError()
            }
        } else {
            Unknown().showError()
        }
    }
}

fun readCommand(): Command {
    print(">_ ")
    val input = readlnOrNull()
    if (!input.isNullOrBlank()) {
        val fullCommandText = input.trim()

        return when (fullCommandText.split(" ").first().lowercase()) {
            "exit" -> Exit(fullCommandText)
            "help" -> Help(fullCommandText)
            "add" -> {
                if (fullCommandText.indexOf("phone") > 0)
                    AddPhone(fullCommandText)
                else if (fullCommandText.indexOf("email") > 0)
                    AddEmail(fullCommandText)
                else Unknown()
            }
            "show" -> Show(fullCommandText)
            "find" -> Find(fullCommandText)
            "export" -> Export(fullCommandText)
            else -> Unknown()
        }
    }
    return Unknown()
}
