package hw3

val PHONE_REGEX = Regex("""add [A-Za-z0-9._-]+ phone \+?[0-9]+""")
val EMAIL_REGEX = Regex("""add [A-Za-z0-9._-]+ email [a-z0-9._-]+@[a-z0-9._-]+.[a-z]+""")
val SHOW_REGEX = Regex("""show [A-Za-z0-9._-]+""")
val FIND_REGEX = Regex("""find [A-Za-z0-9@._-]+""")


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

//    fun getPerson(): Person {
//        val parts = fullCommandText.split(" ")
//        // add name email email
//        return Person(name = parts[1], phone = "", email = parts[3])
//
//    }

    fun addEmail(){
        val parts = fullCommandText.split(" ")
        val name = parts[1]
        val email = parts[3]
        val info = dictionary[name]
        if(info == null){
            dictionary[name] = Info(mutableListOf<String>(), mutableListOf(email))
        }else{
            info.emails.addLast(email)
        }
    }

    fun getText(): String {
        return fullCommandText
    }
}

class AddPhone(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText.matches(PHONE_REGEX)
    }

//    fun getPerson(): Person {
//        val parts = fullCommandText.split(" ")
//        // add name phone phone
//        return Person(name = parts[1], phone = parts[3], email = "")
//    }

    fun addPhone(){
        val parts = fullCommandText.split(" ")
        val name = parts[1]
        val phone = parts[3]
        val info = dictionary[name]
        if(info == null){
            dictionary[name] = Info(mutableListOf(phone), mutableListOf<String>())
        }else{
            info.phones.addLast(phone)
        }
    }

    fun getText(): String {
        return fullCommandText
    }
}

class Show(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText.matches(SHOW_REGEX)
    }
//    companion object {
//        var lastCommand: Person? = null
//    }

    fun showPerson(){
        val parts = fullCommandText.split(" ")
        val name = parts[1]
        val info = dictionary[name]
        if(info == null) println("\"$name\' не найден!")
        else println("$name $info")
    }
}

class Unknown() : Command {
    override fun isValid(): Boolean {
        return false
    }
}

class Find(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText.matches(FIND_REGEX)
    }

    fun findAndShow(){
        val found = fullCommandText.split(" ")[1]
        //val names = mutableListOf<String>()
        val names = mutableSetOf<String>()
        dictionary.forEach{(key, value) ->
                val phones = value.phones
                val emails = value.emails
                if (phones.indexOf(found) >= 0) names.add(key)
                if (emails.indexOf(found) >= 0) names.add(key)
        }

        if(names.isNotEmpty()) println(names)
        else println("\"$found\" не найдено")

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
                is AddEmail -> {
                    command.addEmail()
                    println("Email added")

//                    val person = command.getPerson()
//                    println("Email added to $person")
//                    Show.lastCommand = person
                }
                is AddPhone -> {
                    command.addPhone()
                    println("Phone added")


//                    val person = command.getPerson()
//                    println("Phone added to $person")
//                    Show.lastCommand = person
                }
                is Help -> printHelp()
                is Show -> {
                    command.showPerson()
//                    val cmd = if (Show.lastCommand != null)
//                        "${Show.lastCommand}"
//                    else "Not initialized"
//                    println(cmd)
                }
                is Find -> command.findAndShow()
                is Unknown -> printHelp()
            }
        } else {
            printHelp()
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
            else -> Unknown()
        }
    }
    return Unknown()
}

fun printHelp() {
    println("exit")
    println("help")
    println("add <Имя> phone <Номер телефона>")
    println("add <Имя> email <Адрес электронной почты>")
    println("show")
}