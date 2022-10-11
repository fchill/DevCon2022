package levelBoss

import common.Checkout
import levelBoss.Groupings.Sum
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal.valueOf

class GroupingsTest {

    private val sut = Groupings()

    private fun threeCheckouts() = sequenceOf(
        Checkout("merchant1", valueOf(1)),
        Checkout("merchant2", valueOf(2)),
        Checkout("merchant1", valueOf(3)),
    )

    @Test
    fun sumAmountsForEachMerchant() {
        val actual = sut.sumAmountsForEachMerchant(threeCheckouts())

        actual.forEach { println(it) }
        assertThat(actual).isEqualTo(
            mapOf(
                "merchant1" to Sum(count = 2, totalAmount = valueOf(4)),
                "merchant2" to Sum(count = 1, totalAmount = valueOf(2)),
            )
        )
    }
}