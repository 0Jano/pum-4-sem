package model

import java.time.LocalDate
import kotlin.random.Random

object DataProvider {
    val generalCosts = List(5) {
        Cost(
            CostType.values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(
                2025,
                Random.nextInt(1, 13),
                Random.nextInt(1, 28)
            ),
            Random.nextInt(5000)
        )
    }
}
