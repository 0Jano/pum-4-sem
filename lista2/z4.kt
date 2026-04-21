fun safeParseAndClassify(input: String?): String {
    if (input == null || input.isEmpty()) {
        return "BRAK_DANYCH"
    }

    val number = input.toIntOrNull()
        ?: return "NIEPOPRAWNE_DANE"

    return if (number % 2 == 0) "PARZYSTA" else "NIEPARZYSTA"
}

fun main() {
    println("Input: null -> ${safeParseAndClassify(null)}")
    println("Input: \"\" -> ${safeParseAndClassify("")}")
    println("Input: \"abc\" -> ${safeParseAndClassify("abc")}")
}

