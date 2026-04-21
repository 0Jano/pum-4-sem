fun findDuplicates(list: List<Int>): List<Int> {
    val seen = mutableSetOf<Int>()
    val duplicates = mutableSetOf<Int>()

    list.forEach {
        if (!seen.add (it)) {
            duplicates.add(it)
        }
    }

    return duplicates.toList().sorted()
}

fun main() {
    val input = listOf(1, 2, 3, 4, 5, 2, 3, 6)
    val duplicates = findDuplicates(input)
    println(duplicates)
}