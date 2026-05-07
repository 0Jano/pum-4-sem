package zadania

import model.Cost

fun printAllCosts(costs: List<Cost>) {
    costs
        .sortedBy { it.date }
        .groupBy { it.date.month }
        .toSortedMap()
        .forEach { (month, monthCosts) ->
            println(month)

            monthCosts
                .sortedBy { it.date }
                .forEach { cost ->
                    println(
                        "${cost.date.dayOfMonth.toString().padStart(2, '0')} " +
                        "${cost.type} " +
                        "${cost.amount} zł"
                    )
                }
        }
}
