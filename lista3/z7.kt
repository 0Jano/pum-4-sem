fun srt(lst: List<String>): List<Pair<Char, List<String>>> {
    return lst
        .filter { it.length % 2 == 0 }
        .groupBy { it.first() }
        .toList()
        .sortedBy { it.first }
}

fun main() {
    val input = listOf("apple", "banana", "cherry", "date", "fig", "grape")
    val result = srt(input)
    println(result)
}