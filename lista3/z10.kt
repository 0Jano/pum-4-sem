data class Point(var x: Int, var y: Int) {

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)

    operator fun plus(value: Int): Point = Point(x + value, y + value)

    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)

    operator fun times(other: Point): Point = Point(x * other.x, y * other.y)

    operator fun inc(): Point = Point(x + 1, y + 1)

    operator fun dec(): Point = Point(x - 1, y - 1)

    operator fun not(): Point = Point(-x, -y)

    override fun toString(): String = "($x, $y)"
}

fun main() {
    var p1 = Point(1, 1)
    val p2 = Point(2, 2)

    println(p1 + p2)
    p1 += 1
    println(p1)

    p1 = Point(1, 1)
    println(p1 - p2)

    println(p1 * p2)

    p1 = Point(1, 1)
    p1++
    println(p1)

    p1 = Point(1, 1)
    p1--
    println(p1)

    p1 = Point(1, 1)
    println(!p1)
}