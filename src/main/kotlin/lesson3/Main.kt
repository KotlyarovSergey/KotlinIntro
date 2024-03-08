package lesson3


fun main() {
    val rectangle = Rectangle(10.0, 2.0)
    println(rectangle)
    var sqrt = rectangle.square
    println("Sqr: $sqrt ")

    val rect2 = rectangle.scaled(2.0)
    println(rect2)
    sqrt = rect2.square
    println("Sqr: $sqrt ")

    val square = Square(12.0)
    println(square)
    sqrt = square.square
    println("Sqr: $sqrt ")
    var diag = square.diagonal


    val add = {a: Int, b: Int ->
        if(a < 0)
            b - a
        else
            a + b
    }

    var c = add(2, 3)
    println(c)
    c = add(-5, 3)
    println(c)

    val add2: (Int, Int) -> Int = { a, b -> a + b }
    val cmpr: (Int, Int) -> Boolean = {a,b -> a > b}
//    val dbl: (Int) -> Unit = { it = it * 2 }
    val isPositive : (Int) -> Boolean = {it >= 0}
    println("2 - ${isPositive(2)}, -3 - ${isPositive(-3)}")


    val array = intArrayOf(1,5,645,5,65,4600,54,54,6,54)
    val find = any(array) { it > 2 }
    val exist = cmpr(array, 645) { a, b -> a == b }
    println("find > 2 = $find, exist 645 = $exist")

    println(cmpr(array, 4600) { a, b ->
        a >= b - 2
        a <= b + 3
        //false
    })

//    println(array.cmpr { it == 645 })
}

fun Rectangle.scaled(scale: Double): Rectangle{
    return Rectangle(width * scale, height * scale)
}

val Rectangle.square: Double
    get() = width * height


class Square(val side: Double): Rectangle(side, side) {
    override fun toString(): String {
        return "Square(s: $side)"
    }
}

fun any(array: IntArray, predicate: (Int) -> Boolean) : Boolean{
    for (i in array){
        if (predicate(i))
            return true
    }
    return false
}

fun cmpr(array: IntArray, vl: Int, predicate: (Int, Int) -> Boolean) : Boolean{
    for (i in array){
        if (predicate(i, vl))
            return true
    }
    return false
}