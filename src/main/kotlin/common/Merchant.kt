package common

data class Merchant(
    val id: String,
    val shop: Shop = Shop("HybridCon Webstore"),
) {
    data class Shop(val name: String)
}

object MerchantRepository {

    fun findById(id: String) = Merchant(id)
}