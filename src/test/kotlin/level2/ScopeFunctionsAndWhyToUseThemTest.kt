package level2

import common.Portfolio
import common.PostalAddress
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ScopeFunctionsAndWhyToUseThemTest {

    @BeforeEach
    fun setUp() {
        Portfolio.promotionalMessages.clear()
    }

    @ParameterizedTest
    @MethodSource("variantsForLet")
    fun getMerchantsPromotionalMessage(variant: (String) -> String) {

        val actual = variant("id")

        assertThat(actual).isEqualTo("HybridCon Webstore is the best shop on the web!!!")
    }

    @ParameterizedTest
    @MethodSource("variantsForAlso")
    fun getMerchantsPromotionalMessageWithSideEffect(variant: (String) -> String) {

        val actual = variant("id")

        assertThat(actual)
            .isEqualTo("HybridCon Webstore is the best shop on the web!!!")
        assertThat(Portfolio.promotionalMessages)
            .containsExactly("HybridCon Webstore is the best shop on the web!!!")
    }

    @ParameterizedTest
    @MethodSource("variantsForApply")
    fun initializePortfolio(variant: () -> Unit) {

        variant()

        assertThat(Portfolio.promotionalMessages)
            .containsExactly("Best shop on the Web!")
    }

    @ParameterizedTest
    @MethodSource("variantsForWithAndRun")
    fun renderPostalAddress(variant: (PostalAddress) -> String) {
        val postalAddress = PostalAddress(
            street = "Hamburger Allee",
            streetNr = "22",
            additionalAddressInformation = "",
            zip = "60466",
            city = "Frankfurt",
            company = "HybridCon",
            country = "DE"
        )

        val actual = variant(postalAddress)

        assertThat(actual).isEqualTo("""
                |HybridCon
                |Hamburger Allee 22
                |60466 Frankfurt
                |DE
            """.trimMargin())
    }

    companion object {
        @JvmStatic
        fun variantsForLet(): List<(String) -> String> {
            val sut = ScopeFunctionsAndWhyToUseThem().Let()
            return listOf(
                sut::orderUnreadable,
                sut::redundantVariables,
                sut::getMerchantsPromotionalMessage,
            )
        }

        @JvmStatic
        fun variantsForAlso(): List<(String) -> String> {
            val sut = ScopeFunctionsAndWhyToUseThem().Also()
            return listOf(
                sut::redundantVariablesAndHiddenSideEffect,
                sut::getMerchantsPromotionalMessage,
            )
        }

        @JvmStatic
        fun variantsForApply(): List<() -> Unit> {
            val sut = ScopeFunctionsAndWhyToUseThem().Apply()
            return listOf(
                sut::redundantReferences,
                sut::initializePortfolio,
            )
        }

        @JvmStatic
        fun variantsForWithAndRun(): List<(PostalAddress) -> String> {
            val sut = ScopeFunctionsAndWhyToUseThem().WithAndRun()
            return listOf(
                sut::redundantReferences,
                sut::renderAddress,
                sut::renderByExtensionFunction,
                { sut.renderOptionalAddress(it)!! },
            )
        }
    }
}