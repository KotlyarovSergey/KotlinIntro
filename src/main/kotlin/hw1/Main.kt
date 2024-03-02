package hw1

val PHONE_REGEX = Regex("""add [A-Za-z0-9._-]+ phone \+?[0-9]+""")
val EMAIL_REGEX = Regex("""add [A-Za-z0-9._-]+ email [a-z0-9._-]+@[a-z0-9._-]+.[a-z]+""")

fun main() {



    var input: String? = ""
    while (input != "exit"){
        if(input != null){
            val text = input.trim()
            if(text == "help")
                printHelp()
            else if(text.startsWith("add ")){
                if(text.indexOf(" phone ") > 0){
                    if(text.matches(PHONE_REGEX)){
                        // распарсить
                        // добавить
                        println("Телефон успешно добавлен")
                    } else{
                        //
                        //
                        println("Ошибка добавления телеофна! Команда не распознана. Повторите.")
                    }

                } else if(text.indexOf(" email ") > 0){
                    if(text.matches(EMAIL_REGEX)){
                        // распарсить
                        // добавить
                        println("Почта успешно добавлена")
                    } else{
                        //
                        //
                        println("Ошибка добавления почты! Команда не распознана. Повторите.")
                    }
                } else {
                    println("Ошибка! Команда не распознана. Повторите.")
                }
            }
        }

        print(">_ ")
        input = readlnOrNull()
    }
}

fun printHelp(){
    println("exit")
    println("help")
    println("add <Имя> phone <Номер телефона>")
    println("add <Имя> email <Адрес электронной почты>")
}