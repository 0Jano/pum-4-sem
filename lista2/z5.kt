fun check(n: Int, list: List<Int>): Int {
    for (i in n until list.size) {
        val target = list[i]
        val preamble = list.subList(i - n, i)

        var found = false

        for (j in preamble.indices) {
            for (k in j + 1 until preamble.size) {
                if (preamble[j] + preamble[k] == target) {
                    found = true
                    break
                }
            }
            if (found) break
        }

        if (!found) return target
    }

    return -1
}

fun main(){
    println(check(2, listOf(1, 2, 3, 5, 7, 12, 30)))
    println(check(2, listOf(1, 2, 3, 4, 5, 6)))
    println(check(5, listOf(35, 25, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)))
}
