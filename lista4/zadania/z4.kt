package zadania

import model.Cost

interface CostFormatter {
    fun format(cost: Cost): String
}

object PlCostFormatter : CostFormatter {
    override fun format(cost: Cost): String {
        return "${cost.date.dayOfMonth.toString().padStart(2, '0')} ${cost.type} ${cost.amount} zł"
    }
}

fun formatCosts(costs: List<Cost>, formatter: CostFormatter): String {
    return costs
        .sortedBy { it.date }
        .joinToString("\n") { formatter.format(it) }
}
