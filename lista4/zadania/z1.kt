package zadania

import model.Cost
import java.time.Month

fun groupedCostMap(costs: List<Cost>): Map<Month, List<Cost>> {
    return costs
        .sortedBy { it.date }
        .groupBy { it.date.month }
        .toSortedMap()
}
