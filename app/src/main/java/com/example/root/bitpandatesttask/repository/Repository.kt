package com.example.root.bitpandatesttask.repository

import com.example.root.bitpandatesttask.model.*
import com.example.root.bitpandatesttask.repository.Repository.FilterType.Crypto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class Repository(
    private val webservice: DummyWebService,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getWallets(
        sortType: SortType = SortType.None,
        filterType: FilterType = FilterType.None
    ): List<Wallet> =
        withContext(dispatcher) {
            val wallets = listOf(
                webservice.getCryptoWallets(),
                webservice.getFiatWallets(),
                webservice.getMetalWallets()
            ).flatten().filter { !it.deleted }.filter {
                when (filterType) {
                    Crypto -> it is CryptoWallet
                    FilterType.Fiat -> it is FiatWallet
                    FilterType.Metal -> it is MetalWallet
                    FilterType.None -> true
                }
            }
            return@withContext if (sortType == SortType.Balance)
                wallets.sortedBy { it.balance } else wallets
        }

    suspend fun getWalletPicture(wallet: Wallet) = withContext(dispatcher) {
        when (wallet) {
            is CryptoWallet -> webservice.getCryptoCoins().first { wallet.cryptoCoinId == it.id }.logo
            is MetalWallet -> webservice.getMetals().first { wallet.metalId == it.id }.logo
            is FiatWallet -> webservice.getFiats().first { wallet.fiatId == it.id }.logo
        }
    }

    suspend fun getMetalNameById(id: String) = withContext(dispatcher) {
        webservice.getMetals().first { id == it.id }.name
    }

    fun getDetailText(wallet: Wallet): String = when (wallet) {
        is CryptoWallet ->
            "Coin price ${webservice.getCryptoCoins().first { wallet.cryptoCoinId == it.id }.price}"
        is MetalWallet ->
            "Metal price ${webservice.getMetals().first { wallet.metalId == it.id }.price}"
        is FiatWallet -> "Balance is ${wallet.balance}"
    }

    sealed class SortType {
        object Balance : SortType()
        object None : SortType()
    }

    sealed class FilterType {
        object Crypto : FilterType()
        object Fiat : FilterType()
        object Metal : FilterType()
        object None : FilterType()
    }

}