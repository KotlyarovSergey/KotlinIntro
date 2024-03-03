package lesson2

class Circle(var radius: Double){
    var diameter: Double = 0.0
        get(){
            return radius * 2
        }
    var length: Double = 0.0
        private set
        get() {
            return 2 * Math.PI * radius
        }
    init{
        diameter = radius * 2
        length = 2 * Math.PI * radius
    }

    fun getSqrt(): Double{
        return Math.PI * radius * radius
    }
}