//Написать реализацию класса Holder и интерфейса
//ValueChangeListener, таких, чтобы программа компилировалась, и при
//выполнении функции main на экран было выведено "New value is 1".
//fun main() {
//    val holder = Holder.createHolder(Holder.DEFAULT_NUMBER)
//    holder.number = 9
//    holder.listener = object : ValueChangeListener {
//        override fun onNewValue(number: Int) {
//            println("New value is $number")
//        }
//    }
//    holder.number = 1
//}


interface ValueChangeListener{
    fun onNewValue(number: Int)
}

class Holder private constructor(num: Int){
    var listener: ValueChangeListener? = null
    var number = num
        set(value) {
            listener?.onNewValue(value)
            field = value
        }
    companion object {
        fun createHolder(number: Int): Holder {
            return Holder(number)
        }
        const val DEFAULT_NUMBER: Int = 0
    }
}


fun main() {
    val holder = Holder.createHolder(Holder.DEFAULT_NUMBER)
    holder.number = 9
    holder.listener = object : ValueChangeListener {
        override fun onNewValue(number: Int) {
            println("New value is $number")
        }
    }
    holder.number = 1

    //val h = Holder(12)
    val h = Holder.createHolder(12)
    h.listener = object : ValueChangeListener{
        override fun onNewValue(number: Int) {
            println("new num $number")
        }
    }
    h.number = 10
    println(h.number)
}