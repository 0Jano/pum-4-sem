import model.*
import zadania.*
import java.time.LocalDate
import java.time.Month

fun main() {
    println("Wygenerowane koszty:")
    println(DataProvider.generalCosts)

    println("\n--- Zadanie 1 ---")
    println(groupedCostMap(DataProvider.generalCosts))

    println("\n--- Zadanie 2 ---")
    printAllCosts(DataProvider.generalCosts)

    println("\n--- Zadanie 3 ---")
    val testCosts = listOf(
        Cost(CostType.REFUELING, LocalDate.of(2025, 1, 10), 300),
        Cost(CostType.PARKING, LocalDate.of(2025, 1, 12), 50),
        Cost(CostType.SERVICE, LocalDate.of(2025, 2, 4), 1200)
    )

    println(classifyMonthlyCosts(testCosts, Month.JANUARY, 400))
    println(classifyMonthlyCosts(testCosts, Month.FEBRUARY, 1000))
    println(classifyMonthlyCosts(testCosts, Month.MARCH, 500))

    println("\n--- Zadanie 4 ---")
    val costsForFormatting = listOf(
        Cost(CostType.PARKING, LocalDate.of(2025, 1, 15), 30),
        Cost(CostType.SERVICE, LocalDate.of(2025, 1, 5), 900)
    )

    println(formatCosts(costsForFormatting, PlCostFormatter))
}
