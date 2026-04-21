fun suma(list: List<Int>): Int {
    return list.filter { it > 0 }.sum()
}

fun main() {
    val input = listOf(-1, 2, -3, 4, 5)
    val result = suma(input)
    println(result) 
}