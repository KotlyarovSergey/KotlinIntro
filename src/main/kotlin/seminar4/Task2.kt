package seminar4

import java.lang.IndexOutOfBoundsException


//class NotIntArgumentException(message: String?): RuntimeException(message)
class NotIntArgumentException(message: String): RuntimeException(message)

fun sum(a: String, b: String):Int{
    val numA: Int
    val numB: Int

    try {
        numA = a.toInt()
        numB = b.toInt()
    } catch (except: NumberFormatException){
//        throw NotIntArgumentException(except.message)
        throw NotIntArgumentException("Unable convert to Int")
    }
    return numA + numB
}
fun main(vararg args: String) {
    println("start")
    try {
        val num1 = args[0]
        val num2 = args[1]
        println(sum(num1, num2))
    } catch (ex: IndexOutOfBoundsException){
        println(ex.message.toString())
    } catch (ex: NotIntArgumentException){
        println(ex.message.toString())
    }
    println("finish")
}