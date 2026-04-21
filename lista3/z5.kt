fun evenPositiveSquare(lst: List<Int>): List<Int> {
    return lst
        .filterIndexed { index, value -> index % 2 == 1 && value > 0 }
        .map { it * it }
}

fun main() {
    val input = listOf(-1, 2, -3, 4, 5, 6)
    val result = evenPositiveSquare(input)
    println(result) 
}