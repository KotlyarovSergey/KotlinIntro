package lesson2

class ClassA(a: Int, val b: Double, var c: String){

    val d: Int
    var e: Int
    init {
        d = a
        e = a
    }

}

class ClassB(a: Int) {

    val i: Int
    val d: Double

    init {
        this.i = a
        this.d = a.toDouble()
    }

    constructor(d: Double): this(d.toInt()){
        // тело второго конструктора
    }
}


// класс без контруктора по-умолчанию и 2мя перегруженными
class ClassC{
    val a: Int
    val b: Double

    constructor(a: Int){
        this.a = a
        this.b = a.toDouble()
    }

    constructor(b: Double){
        this.a = b.toInt()
        this.b = b
    }

}





fun main(){

    val clA = ClassA(10, 12.3, "aaa")
    //clA.a               // вообще не виден, т.к. это ПАРАМЕТР конструкора. Полем класса не является!!!
    val b = clA.b       // чтение ОК
    //clA.b = 20          // только для чтения, потому что val
    clA.c = "30"        // чтение и запись ОК, потому что var
    val d = clA.d       // только для чтения, потому что val
    clA.e = 123         // чтение и запись ОК, потому что var




}