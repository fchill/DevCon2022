package level2

import common.Merchant
import common.MerchantRepository
import common.Portfolio
import common.PostalAddress

class ScopeFunctionsAndWhyToUseThem {

    inner class Let {

        fun orderUnreadable(id: String) =
            extractShop(MerchantRepository.findById(id)).name +
                    "is the best shop on the web!!!"

        fun redundantVariables(id: String): String {
            val merchant = MerchantRepository.findById(id)
            val shop = extractShop(merchant)
            return "${shop.name} is the best shop on the web!!!"
        }

        fun getMerchantsPromotionalMessage(id: String) =
            MerchantRepository.findById(id)
                .let { extractShop(it) }
                .let { "${it.name} is the best shop on the web!!!" }
    }

    inner class Also {

        fun redundantVariablesAndHiddenSideEffect(id: String): String {
            val merchant = MerchantRepository.findById(id)
            val shop = extractShop(merchant)
            val promotionalMessage = "${shop.name} is the best shop on the web!!!"
            Portfolio.promotionalMessages.add(promotionalMessage)
            return promotionalMessage
        }

        fun getMerchantsPromotionalMessage(id: String) =
            MerchantRepository.findById(id)
                .let { extractShop(it) }
                .let { "${it.name} is the best shop on the web!!!" }
                .also { Portfolio.promotionalMessages.add(it) }
    }

    inner class Apply {

        fun redundantReferences() {
            Portfolio.promotionalMessages.add("Best shop in town!")
            Portfolio.promotionalMessages.add("Best shop in Germany!")
            Portfolio.promotionalMessages.clear()
            Portfolio.promotionalMessages.add("Best shop on the Web!")
        }

        fun initializePortfolio() {
            Portfolio.promotionalMessages.apply {
                add("Best shop in town!")
                add("Best shop in Germany!")
                clear()
                add("Best shop on the Web!")
            }
        }
    }

    inner class WithAndRun {

        fun redundantReferences(address: PostalAddress) =
            """
                |${address.company}
                |${address.street} ${address.streetNr}
                |${address.zip} ${address.city}
                |${address.country}
            """.trimMargin()

        fun renderAddress(address: PostalAddress) =
            with(address) {
                """
                    |$company
                    |$street $streetNr
                    |$zip $city
                    |$country
                """.trimMargin()
            }

        fun renderOptionalAddress(address: PostalAddress?) =
            address?.run {
                """
                    |$company
                    |$street $streetNr
                    |$zip $city
                    |$country
                """.trimMargin()
            }

        private fun PostalAddress.render() =
            """
                |$company
                |$street $streetNr
                |$zip $city
                |$country
            """.trimMargin()

        fun renderByExtensionFunction(postalAddress: PostalAddress) =
            postalAddress.render()


        // BACKUP


        // Executing a lambda on non-null objects: LET
        // Introducing an expression as a variable in local scope: LET
        // Object configuration: APPLY
        // Additional effects: ALSO
        // Object configuration and computing the result: RUN
        // Grouping function calls on an object: WITH
    }

    private fun extractShop(merchant: Merchant) = merchant.shop
}