package com.example.root.bitpandatesttask.model

sealed class Wallet {
    abstract val id: String
    abstract val name: String
    abstract val balance: Double
    abstract val isDefault: Boolean
    abstract val deleted: Boolean
}

data class CryptoWallet(
    override val id: String,
    override val name: String,
    override val balance: Double,
    override val isDefault: Boolean,
    override val deleted: Boolean,

    val cryptoCoinId: String
) : Wallet()

data class FiatWallet(
    override val id: String,
    override val name: String,
    override val balance: Double,
    override val isDefault: Boolean,
    override val deleted: Boolean,

    val fiatId: String
) : Wallet()

data class MetalWallet(
    override val id: String,
    override val name: String,
    override val balance: Double,
    override val isDefault: Boolean,
    override val deleted: Boolean,

    val metalId: String
): Wallet()

