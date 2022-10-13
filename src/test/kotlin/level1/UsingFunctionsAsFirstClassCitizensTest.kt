package level1

import org.junit.jupiter.api.Test

class UsingFunctionsAsFirstClassCitizensTest {

    private val sut = UsingFunctionsAsFirstClassCitizens()

    @Test
    fun codeOrderingVersusOptimization() {
        sut.codeOrderingVersusOptimization()
    }

    @Test
    fun optimizationVersusCodeOrdering() {
        sut.optimizationVersusCodeOrdering()
    }

    @Test
    fun resolution() {
        sut.resolution()
    }


    // BACKUP


    @Test
    fun doingItTheLazyWay() {
        sut.doingItTheLazyWay()
    }
}