package level3

import common.Checkout
import common.Merchant
import common.Portfolio
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

class FunctionalCollectionAPIs {

    fun transformWithMap(merchants: Sequence<Merchant>) =
        merchants.map { it.shop }

    fun consumeWithForEach(merchants: Sequence<Merchant>) =
        merchants.forEach {
            Portfolio.promotionalMessages.add("${it.shop.name} is the best shop on the web!!!")
        }

    fun containsAnyCheckoutWithAmountZero(checkouts: Sequence<Checkout>) =
        checkouts.any { it.amount == ZERO }

    fun findFirstCheckoutWithAmountZero(checkouts: Sequence<Checkout>) =
        checkouts.firstOrNull { it.amount == ZERO }

    fun sumAmounts(checkouts: Sequence<Checkout>) =
        checkouts.fold(Sum()) { acc, it -> acc.incorporate(it.amount) }
    //fun sumAmounts(checkouts: Sequence<Checkout>) = checkouts.sumOf { it.amount }

    data class Sum(
        val count: Int = 0,
        val totalAmount: BigDecimal = ZERO,
    ) {

        fun incorporate(amount: BigDecimal) = Sum(
            count + 1,
            totalAmount + amount,
        )
    }
}