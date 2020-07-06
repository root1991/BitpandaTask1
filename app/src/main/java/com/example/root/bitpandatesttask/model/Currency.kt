package com.example.root.bitpandatesttask.model

sealed class Currency {
    abstract val name: String
    abstract val symbol: String
    abstract val id: String
    abstract val logo: String
}

data class Fiat(
    override val name: String,
    override val symbol: String,
    override val id: String,
    override val logo: String
) : Currency()

data class Metal(
    override val name: String,
    override val symbol: String,
    override val id: String,
    override val logo: String,
    val price: Double
): Currency()

data class CryptoCoin(
    override val name: String,
    override val symbol: String,
    override val id: String,
    override val logo: String,
    val price: Double
): Currency()

