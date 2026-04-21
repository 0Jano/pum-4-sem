fun countElements(input: List<List<String>>): Map<String, Int> {
    return input
        .flatten()
        .groupingBy { it }
        .eachCount()
}

fun main() {
    val input = listOf(
        listOf("apple", "banana", "orange"),
        listOf("banana", "grape")
    )
    val result = countElements(input)
    println(result)
}