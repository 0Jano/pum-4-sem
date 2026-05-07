package zadania

import model.Cost
import java.time.Month

sealed class MonthlyCostStatus {
    data object NoCosts : MonthlyCostStatus()
    data class WithinLimit(val total: Int) : MonthlyCostStatus()
    data class OverLimit(val total: Int, val exceededBy: Int) : MonthlyCostStatus()
}

fun classifyMonthlyCosts(
    costs: List<Cost>,
    month: Month,
    limit: Int
): MonthlyCostStatus {
    val monthlyCosts = costs
        .filter { it.date.month == month }

    val total = monthlyCosts
        .sumOf { it.amount }

    return when {
        monthlyCosts.isEmpty() -> MonthlyCostStatus.NoCosts
        total <= limit -> MonthlyCostStatus.WithinLimit(total)
        else -> MonthlyCostStatus.OverLimit(total, total - limit)
    }
}
