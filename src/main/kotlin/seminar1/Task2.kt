package seminar1

fun main() {
    println("sumAll = ${sumAll(1, 5, 20)}")
    println("sumAll = ${sumAll()}")
    println("sumAll = ${sumAll(2, 3, 4, 5, 6, 7)}")
    println(createOutputString("Alice"))
    println(createOutputString("Bob", 23))
    println(createOutputString(isStudent = true, name = "Carol", age = 19))
    println(createOutputString("Daniel", 32, isStudent = null))
    println(multiplyBy(null, 4))
    println(multiplyBy(3, 4))
}

fun sumAll(vararg args: Int): Int{
    var summ: Int = 0
    for(e in args){
        summ += e
    }
    return summ
}

fun createOutputString(name: String, age: Int = 42, isStudent: Boolean? = null): String{
    var str = ""
    if(isStudent == true)
        str += "student "

    str += "$name has age of $age"

    return str
}

fun multiplyBy(a: Int?, b: Int): Int?{
//    return when(a){
//        null -> null
//        else -> a * b
//    }

    return if(a == null)
        null
    else
        a * b
}