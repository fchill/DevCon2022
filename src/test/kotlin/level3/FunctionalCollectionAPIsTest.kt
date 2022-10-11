package level3

import common.Checkout
import common.Merchant
import common.Merchant.Shop
import common.Portfolio
import level3.FunctionalCollectionAPIs.Sum
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal.ZERO
import java.math.BigDecimal.valueOf

class FunctionalCollectionAPIsTest {

    private val sut = FunctionalCollectionAPIs()

    @BeforeEach
    fun setUp() = Portfolio.promotionalMessages.clear()

    @Test
    fun transformWithMap() {
        val shop = Shop("myShop")
        val merchant = Merchant("id", shop)

        val actual = sut.transformWithMap(sequenceOf(merchant))

        assertThat(actual.toList()).containsExactly(shop)
    }

    @Test
    fun consumeWithForEach() {
        val sequence = generateSequence { Merchant("id", Shop("MyShop")) }
            .take(10)

        sut.consumeWithForEach(sequence)

        assertThat(Portfolio.promotionalMessages)
            .hasSize(10)
            .containsOnly("MyShop is the best shop on the web!!!")
    }

    private fun threeCheckouts() = (0L..2L)
        .map { Checkout("merchant-$it", valueOf(it)) }
        .reversed()
        .asSequence()

    @Test
    fun reduceToBooleanWithAny() {
        assertThat(sut.containsAnyCheckoutWithAmountZero(threeCheckouts()))
            .isTrue()
    }

    @Test
    fun findAnElementWithFirst() {
        assertThat(sut.findFirstCheckoutWithAmountZero(threeCheckouts()))
            .isEqualTo(Checkout("merchant-0", ZERO))
    }

    @Test
    fun sumWithFold() {
        assertThat(sut.sumAmounts(threeCheckouts()))
            .isEqualTo(Sum(count = 3, totalAmount = valueOf(3)))
    }
}