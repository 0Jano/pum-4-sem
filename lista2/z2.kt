val <T> List<T>.head: T
    get() = this.first()
val <T> List<T>.tail: List<T>
    get() = this.drop(1)

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    println(list.head)
    println(list.tail)
}