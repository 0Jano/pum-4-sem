package model

import java.time.LocalDate

data class Cost(
    val type: CostType,
    val date: LocalDate,
    val amount: Int
)
