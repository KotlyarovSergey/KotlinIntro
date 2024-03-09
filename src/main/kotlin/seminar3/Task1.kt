package seminar3

//import kotlin.math.min

//1. Количество людей в городе London в возрасте от 21 до 30 лет
//включительно.
//2. Список имён без повторений для города Berlin.
//3. Список городов, в которых есть хотя бы один человек по
//имени Oliver.

data class Entry(
    val city: String,
    val name: String,
    val age: Int
)

val entries = listOf(
    Entry("London", "Liam", 28),
    Entry("Berlin", "Oliver", 24),
    Entry("Madrid", "Oliver", 45),
    Entry("Berlin", "Jerry", 29),
    Entry("London", "Oliver", 19),
    Entry("Madrid", "Kate", 16),
    Entry("Berlin", "Oliver", 34),
    Entry("London", "Kate", 25),
    Entry("Berlin", "Oliver", 23),
    Entry("Milan", "Jerry", 28),
    Entry("Paris", "Charles", 44)
)

fun main() {
    val london = entries.filter {it.city == "London" }
    val toAge = london.filter { it.age in 21..29 }
    val count = toAge.size
    println(toAge)

    val cnt = entries
        .filter { it.city == "London" }
        .filter { it.age in 21..29 }
        .size
    println(cnt)

    val cnt2 = entries.asSequence()
        .filter { it.city == "London" }
        .filter { it.age in 21..29 }
        .count()



    val lst = listOf("asdf", "asd", "klk", "kljk")
    lst.reduce{ a, b -> "$a $b"}

    val minim: (Int, Int) -> Int = {a, b -> if(a > b) b else a}
    val lst2 = listOf(1,4,5,3)
    lst2.reduce { a, b -> minim(a, b) }
    lst2.reduce { a, b -> if(a < b) a else b }

    // 2. Список имён без повторений для города Berlin.
    val setName = entries.asSequence()
        .filter { it.city == "Berlin" }
        .map { it.name }
        //.toList()
        .toSet()
    println(setName)


    //3. Список городов, в которых есть хотя бы один человек по имени Oliver.
    val cities = entries.asSequence()
        .filter { it.name == "Oliver" }
        .map { it.city }
        .distinct()
    println(cities)
}














