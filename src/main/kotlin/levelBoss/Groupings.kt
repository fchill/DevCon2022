package levelBoss

import common.Checkout
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

class Groupings {

    fun sumAmountsForEachMerchant(checkouts: Sequence<Checkout>) =
        checkouts
            .groupingBy { it.merchantId }
            .fold(Sum()) { acc, it -> acc.incorporate(it.amount) }

    data class Sum(
        val count: Int = 0,
        val totalAmount: BigDecimal = ZERO,
    ) {

        fun incorporate(amount: BigDecimal) = Sum(
            count + 1,
            totalAmount + amount,
        )
    }

    data class MutableSum(
        var count: Int = 0,
        var totalAmount: BigDecimal = ZERO,
    ) {

        fun incorporate(amount: BigDecimal): MutableSum {
            count++
            totalAmount += amount
            return this
        }
    }
}
