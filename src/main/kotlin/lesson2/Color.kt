package lesson2

enum class Color(
    val r: Int,
    val g: Int,
    val b: Int
){
    GREEN(0, 255, 0),
    RED(255, 0, 0),
    BLUE(0,0,255);

    fun getCode(): Int{
        return r * 65563 + g * 256 + b
    }
}
