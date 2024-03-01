package seminar1

fun main() {
    printStars(5, 2, 2)
    println()
    printStars(1, 3, 2)
    println()
    printStars(1, 2, 4)
}

fun printStars(a: Int, b: Int, c: Int) {
    var stars = a
    for (k in 0..<b) {
        printLine(a + c * k)
    }
    for (k in b downTo 0) {
        printLine(a + c * k)
    }
}

fun printLine(n: Int) {
    val arr = CharArray(n)
    arr.fill('*')
    println(arr.joinToString(""))
}