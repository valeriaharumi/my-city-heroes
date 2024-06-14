package com.github.valeriaharumi.heroes_mobile.model

data class Report(
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val status: String,
    val city: String,
    val category: String
)