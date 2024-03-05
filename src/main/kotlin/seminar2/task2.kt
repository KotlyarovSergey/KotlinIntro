package seminar2

sealed interface Operation {
    fun calculate(): Double
}

class Value(val d: Double) : Operation {
    override fun calculate(): Double {
        return d
    }

}

class Multiply(val first: Operation, val second: Operation) : Operation {
    override fun calculate(): Double = first.calculate() * second.calculate()

}

class Plus(val first: Operation, val second: Operation) : Operation {
    override fun calculate(): Double = first.calculate() + second.calculate()

}


fun printOperationResult(operation: Operation) {
    val result = operation.calculate()
    println(result)
}

fun main() {
    // Соответствует формуле 4 + 2.5 * 2
    printOperationResult(
        Plus(
            Value(4.0),
            Multiply(
                Value(2.5),
                Value(2.0)
            )
        )
    )


    // Соответствует формуле (1 + 3.5) * (2.5 + 2)
    printOperationResult(
        Multiply(
            Plus(
                Value(1.0),
                Value(3.5)
            ),
            Plus(
                Value(2.5),
                Value(2.0)
            )
        )
    )

    // Соответствует формуле 10
    printOperationResult(
        Value(10.0)
    )

}




