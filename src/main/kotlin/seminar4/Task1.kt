package seminar4

interface SimpleLogger {
    val prefix: String
    fun log(message: String) {
        println(prefix + message)
    }
}

class SimpleLoggerImpl(
    override val prefix: String
) : SimpleLogger {
    init {
        println("SimpleLoggerImpl created")
    }
}

class StackTraceLogger(log: SimpleLoggerImpl) : SimpleLogger by log {
    override fun log(message: String) {
        super.log(message)
        val stackTrace = Thread.currentThread().stackTrace
        println("--------------------")
//        for (stake in stackTrace){
//            println(stake.toString())
//        }
        stackTrace.forEachIndexed { index, it ->
            if (index > 0)
                println(it.toString())
        }
        println("--------------------")

    }
}


// TODO StackTraceLogger
val stackTraceLogger by lazy {
    StackTraceLogger(
        SimpleLoggerImpl("MyLogger: ")
    )
}

//val stackTraceLogger = StackTraceLogger(
//    SimpleLoggerImpl("MyLogger: ")
//)


fun main() {
    println("Enter main()")
    stackTraceLogger.log("Hello world")
}