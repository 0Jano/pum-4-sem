data class StudentScore(val name: String, val subject: String, val score: Int)

fun analyzeResults(
    students: List<StudentScore>
): Triple<Map<String, List<StudentScore>>, List<StudentScore>, List<String>> {

    val passed = students.filter { it.score >= 50 }
    val failed = students.filter { it.score < 50 }

    val passedBySubject = passed.groupBy { it.subject }

    val subjectsAllPassed = students
        .groupBy { it.subject }
        .filter { (_, scores) -> scores.all { it.score >= 50 } }
        .keys
        .toList()

    return Triple(passedBySubject, failed, subjectsAllPassed)
}

fun main() {
    val students = listOf(
        StudentScore("Alice", "Math", 85),
        StudentScore("Bob", "Math", 45),
        StudentScore("Charlie", "Math", 55),
        StudentScore("Alice", "Science", 90),
        StudentScore("Bob", "Science", 60),
        StudentScore("Charlie", "Science", 40),
        StudentScore("Alice", "History", 70),
        StudentScore("Bob", "History", 80),
        StudentScore("Charlie", "History", 75)
    )

    val (passedBySubject, failed, subjectsAllPassed) = analyzeResults(students)

    println("Passed by Subject:")
    passedBySubject.forEach { (subject, scores) ->
        println("$subject: ${scores.map { it.name }}")
    }

    println("\nFailed Students:")
    failed.forEach { println("${it.name} in ${it.subject} with score ${it.score}") }

    println("\nSubjects where all students passed: $subjectsAllPassed")
}