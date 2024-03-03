package lesson2


@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val rectangle = Rectangle(4.0, 3.0)
    println("h: ${rectangle.height}, w: ${rectangle.width}")
    println(rectangle.diagonal)

    val circle = Circle(2.3)
    println("Circle: r: ${circle.radius}, d: ${circle.diameter}, s: ${circle.getSqrt()}")

//    circle.length = 12.2
    println("r: ${circle.radius}, l: ${circle.length}")
    circle.radius = 3.2
    println("r: ${circle.radius}, l: ${circle.length}")

    val person = Person("Anna", 23, "+72251165113")
    println(person)
    val oldAnna = person.copy(age = 25)
    println(oldAnna)

    val(name, age, phone) = oldAnna
    println("n: $name, a: $age, p: $phone")

    val(_, ageYoung, _) = person
    println(ageYoung)

    val color = Color.GREEN
    println(Integer.toHexString(color.getCode()))
    println(color.getCode().toHexString(HexFormat.UpperCase))


    val appSet = AppSetting
    appSet.lastLogin = "Sergey"
    println(AppSetting.lastLogin)

    AppSetting.lastLogin = "Andrey"
    println(AppSetting.lastLogin)

}
