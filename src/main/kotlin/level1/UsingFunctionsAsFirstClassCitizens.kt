package level1

import common.Merchant
import common.MerchantRepository

class UsingFunctionsAsFirstClassCitizens {

    fun commonInFrontendDevelopment() {
        var clickCounter = 0

        val incrementCounter = { clickCounter += 1 }

        Button(onClickHandler = incrementCounter)
    }

    fun codeOrderingVersusOptimization() {
        println("Collecting buyer data")
        val fingerprint = expensiveBrowserFingerprint() // Only do this if you really have to!

        println("Collecting merchant data")

        println("Performing some checks that possibly throw")
        performACheckBasedOnFingerprint(fingerprint)
    }

    fun optimizationVersusCodeOrdering() {
        println("Collecting buyer data")

        println("Collecting merchant data")

        println("Performing some checks that possibly throw")
        val fingerprint = expensiveBrowserFingerprint() // This is part of collecting buyer data!
        performACheckBasedOnFingerprint(fingerprint)
    }

    fun resolution() {
        println("Collecting buyer data")
        val fingerprint = { expensiveBrowserFingerprint() }

        println("Collecting merchant data")

        println("Performing some checks that possibly throw")
        performACheckBasedOnFingerprint(fingerprint())
    }

    fun doingItTheLazyWay() {
        println("Collecting buyer data")
        val fingerprint = lazy { expensiveBrowserFingerprint() }

        println("Collecting merchant data")

        println("Performing some checks that possibly throw")
        performACheckBasedOnFingerprint(fingerprint)
    }


    fun minimalRightsForClasses() {
        OnlyNeedsToReadById(MerchantRepository::findById)
            .doSomething()
    }


    class OnlyNeedsToReadById(
        private val findMerchantById: (String) -> Merchant,
    ) {

        fun doSomething() {
            println("Accessing the repository, but definitely not writing")
            findMerchantById("merchantId")
        }
    }


    private fun performACheckBasedOnFingerprint(fingerprint: BrowserFingerprint) {
        println("Working with fingerprint $fingerprint")
    }

    private fun performACheckBasedOnFingerprint(fingerprint: Lazy<BrowserFingerprint>) {
        println("Working with fingerprint ${fingerprint.value}")
        println("Working with fingerprint ${fingerprint.value} again")
    }

    private fun expensiveBrowserFingerprint(): BrowserFingerprint {
        println("Getting expensive buyer's fingerprint")
        return BrowserFingerprint()
    }

    class Button(val onClickHandler: () -> Unit)

    class BrowserFingerprint {
        override fun toString(): String {
            return "858e8760-6da2-4fc9-816d-fbbe9658335d"
        }
    }
}