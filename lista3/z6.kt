fun perm(lst: List<Int>): List<List<Int>> {
    if (lst.isEmpty()) return listOf(emptyList())
    if (lst.size == 1) return listOf(lst)

    return lst.flatMap { element ->
        perm(lst - element).map { listOf(element) + it }
    }
}

fun main() {
    val input = listOf(1, 2, 3)
    val permutations = perm(input)
    println(permutations)
}