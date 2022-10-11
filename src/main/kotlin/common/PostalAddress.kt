package common

data class PostalAddress(
    val street: String,
    val streetNr: String,
    val additionalAddressInformation: String,
    val zip: String,
    val city: String,
    val company: String,
    val country: String,
)