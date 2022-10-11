package common

import java.math.BigDecimal

data class Checkout(
    val merchantId: String,
    val amount: BigDecimal,
)