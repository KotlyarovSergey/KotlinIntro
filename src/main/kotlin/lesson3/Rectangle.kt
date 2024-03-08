package lesson3

import kotlin.math.sqrt

open class Rectangle(
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

    override fun toString(): String {
        return "Rectangle(w: $width, h: $height)"
    }

}