package seminar1

fun main(vararg args: String) {
    if(args.size >= 0){
        val a = args[0].toIntOrNull()
        val b = args[1].toIntOrNull()
        if(a != null && b != null){
            val sum: Int = a + b
            println("$a + $b + $sum")
        }
    }
    println("Неврные аргумнты")

}
