package com.example.root.bitpandatesttask

import com.example.root.bitpandatesttask.model.CryptoWallet
import com.example.root.bitpandatesttask.model.DummyWebService
import com.example.root.bitpandatesttask.model.FiatWallet
import com.example.root.bitpandatesttask.model.MetalWallet
import com.example.root.bitpandatesttask.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class RepositoryTest {

    private val webService = mockk<DummyWebService>()
    private val repo = Repository(webService, TestCoroutineDispatcher())

    private val metalWallets = listOf(
        MetalWallet(
            "1", "Name1",
            0.3,
            isDefault = true,
            deleted = false,
            metalId = "1"
        )
    )

    private val cryptoWallets = listOf(
        CryptoWallet(
            "1", "Name1",
            0.5,
            isDefault = true,
            deleted = false,
            cryptoCoinId = "1"
        )
    )
    private val fiatWallets = listOf(
        FiatWallet(
            "1", "Name1",
            0.1,
            isDefault = true,
            deleted = false,
            fiatId = "1"
        )
    )

    private val dummyData = listOf(metalWallets, cryptoWallets, fiatWallets).flatten()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { webService.getMetalWallets() } returns metalWallets
        coEvery { webService.getCryptoWallets() } returns cryptoWallets
        coEvery { webService.getFiatWallets() } returns fiatWallets
    }

    @Test
    fun `wallets sort by balance works correct`() = runBlockingTest {
        val wallets = repo.getWallets(sortType = Repository.SortType.Balance)

        assertEquals(dummyData.sortedBy { it.balance }, wallets)
    }

    @Test
    fun `wallets are filtered by metal`() = runBlockingTest {
        val wallets = repo.getWallets(filterType = Repository.FilterType.Metal)

        assertEquals(metalWallets, wallets)
    }

    @Test
    fun `wallets are filtered by crypto`() = runBlockingTest {
        val wallets = repo.getWallets(filterType = Repository.FilterType.Crypto)

        assertEquals(cryptoWallets, wallets)
    }

    @Test
    fun `wallets are filtered by fiat`() = runBlockingTest {
        val wallets = repo.getWallets(filterType = Repository.FilterType.Fiat)

        assertEquals(fiatWallets, wallets)
    }
}
