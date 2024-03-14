package hw4

import java.lang.StringBuilder


interface JsonElement {
    fun render(builder: StringBuilder): String
}


class JsonObjectElement : JsonElement {
    private val children = mutableMapOf<String, JsonElement>()
    override fun render(builder: StringBuilder) = builder
        .append("{")
        .append(children.entries.foldIndexed(StringBuilder(), { index, acc, entry ->
            acc.apply {
                val renderedChild = "\"${entry.key}\":${entry.value.render(StringBuilder())}"
                append(renderedChild)
                if (index < children.size - 1) {
                    append(",")
                }
            }
        }).toString())
        .append("}")
        .toString()

    fun objectElement(name: String, body: JsonObjectElement.() -> Unit) {
        val jsonObjectElement = JsonObjectElement()
        jsonObjectElement.body()
        children[name] = jsonObjectElement
    }
    fun arrayElement(name: String, body: JsonArrayElement.() -> Unit) {
        val jsonArrayElement = JsonArrayElement()
        jsonArrayElement.body()
        children[name] = jsonArrayElement
    }

}

class JsonArrayElement : JsonElement {
    private val children = mutableListOf<JsonElement>()
    override fun render(builder: StringBuilder) = builder
        .append("[")
        .append(children.foldIndexed(StringBuilder(), { index, acc, jsonElement ->
            acc.apply {
                append(jsonElement.render(StringBuilder()))
                if (index < children.size - 1) {
                    append(",")
                }
            }
        }))
        .append("]")
        .toString()

    fun stringElement(value: String) {
        children.add(JsonStringElement(value))
    }
}


class JsonStringElement(private val value: String) : JsonElement {
    override fun render(builder: StringBuilder) =
        builder.append("\"$value\"").toString()
}

fun json(body: JsonObjectElement.() -> Unit): String {
    val jsonObjectElement = JsonObjectElement()
    val stringBuilder = StringBuilder()
    jsonObjectElement.body()
    jsonObjectElement.render(stringBuilder)
    return stringBuilder.toString()
}