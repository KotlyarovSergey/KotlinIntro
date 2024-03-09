package hw2

val PHONE_REGEX = Regex("""add [A-Za-z0-9._-]+ phone \+?[0-9]+""")
val EMAIL_REGEX = Regex("""add [A-Za-z0-9._-]+ email [a-z0-9._-]+@[a-z0-9._-]+.[a-z]+""")

data class Person(
    val name: String,
    val phone: String,
    val email: String
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

    fun getPerson(): Person {
        val parts = fullCommandText.split(" ")
        // add name email email
        return Person(name = parts[1], phone = "", email = parts[3])
    }

    fun getText(): String {
        return fullCommandText
    }
}

class AddPhone(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText.matches(PHONE_REGEX)
    }

    fun getPerson(): Person {
        val parts = fullCommandText.split(" ")
        // add name phone phone
        return Person(name = parts[1], phone = parts[3], email = "")
    }

    fun getText(): String {
        return fullCommandText
    }
}

class Show(private val fullCommandText: String) : Command {
    override fun isValid(): Boolean {
        return fullCommandText == "show"
    }
    companion object {
        var lastCommand: Person? = null
    }
}

class Unknown() : Command {
    override fun isValid(): Boolean {
        return false
    }
}


fun main() {
    while (true) {
        // a. Читаем команду с помощью функции readCommand
        val command = readCommand()
        // b. Выводим на экран получившийся экземпляр Command
        println(command)
        // c. Если isValid для команды возвращает false, выводим help.
        //    Если true, обрабатываем команду внутри when.
        if (command.isValid()) {
            when (command) {
                is Exit -> return
                is AddEmail -> {
                    val person = command.getPerson()
                    println("Email added to $person")
                    Show.lastCommand = person
                }
                is AddPhone -> {
                    val person = command.getPerson()
                    println("Phone added to $person")
                    Show.lastCommand = person
                }
                is Help -> printHelp()
                is Show -> {
                    val cmd = if (Show.lastCommand != null)
                        "${Show.lastCommand}"
                    else "Not initialized"
                    println(cmd)
                }
                is Unknown -> TODO()
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
        val fullCommand = input.trim().lowercase()
        val startWith = fullCommand.split(" ").first()

        return when (startWith) {
            "exit" -> Exit(fullCommand)
            "help" -> Help(fullCommand)
            "add" -> {
                if (fullCommand.indexOf("phone") > 0)
                    AddPhone(fullCommand)
                else if (fullCommand.indexOf("email") > 0)
                    AddEmail(fullCommand)
                else Unknown()
            }

            "show" -> Show(fullCommand)
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