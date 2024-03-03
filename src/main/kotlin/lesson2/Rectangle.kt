package lesson2

import kotlin.math.sqrt

class Rectangle(
    var width: Double,
    var height: Double
) {
    var diagonal: Double
        get() {
            return sqrt(width * width + height * height)
        }

    init {
        diagonal = sqrt(width * width + height * height)
    }

}