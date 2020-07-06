package com.example.root.bitpandatesttask.model

class DummyWebService {

    fun getCryptoWallets(): List<CryptoWallet> = DummyData.dummyCryptoWalletList

    fun getMetalWallets(): List<MetalWallet> = DummyData.dummyMetalWalletList

    fun getFiatWallets(): List<FiatWallet> = DummyData.dummyEURWallet

    fun getCryptoCoins(): List<CryptoCoin> = DummyData.cryptoCoins

    fun getMetals(): List<Metal> = DummyData.metals

    fun getFiats(): List<Fiat> = DummyData.fiats

    fun getCurrencies(): List<Currency> = listOf(
        DummyData.cryptoCoins,
        DummyData.metals,
        DummyData.fiats
    ).flatten()

}