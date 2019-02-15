package kchaiko.vandrouki

import android.content.Context
import android.net.ConnectivityManager
import kchaiko.vandrouki.di.getAppGraph
import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.network.service.VandSiteService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.with
import org.koin.core.Koin
import org.koin.core.parameter.emptyParameterDefinition
import org.koin.standalone.StandAloneContext
import org.koin.standalone.get
import org.koin.test.AutoCloseKoinTest
import org.koin.test.ext.koin.beanDefinitions
import org.mockito.Mockito

class NetworkTest : AutoCloseKoinTest() {

    @Before
    fun setup() {
        val androidContext = Mockito.mock(Context::class.java)
        Mockito.`when`(androidContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(Mockito.mock(ConnectivityManager::class.java))
        StandAloneContext.startKoin(list = getAppGraph())
                .with(androidContext)
    }

    @Test
    fun `pre-condition dependency`() {
        val koinContext = StandAloneContext.getKoin().koinContext
        koinContext.instanceRegistry.createInstances(koinContext.beanDefinitions(), emptyParameterDefinition())
    }

    @Test
    fun `pre-condition requests`() {
        val service = get<RetrofitManager>().create(VandSiteService::class.java)
        runBlocking {
            val discountList = service.htmlDiscountList(1).await()
            assert(discountList.isNotEmpty())
            Koin.logger.info("Discount list, size: ${discountList.size}")

            val detailedDiscount = service.htmlDetailedDiscount(discountList.first().detailUrlPart).await()
            assert(detailedDiscount.fullDesc.isNotEmpty())
            Koin.logger.info("Detailed discount, description is not empty: ${detailedDiscount.fullDesc.isNotEmpty()}")
        }
    }

}