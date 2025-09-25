package com.calyrsoft.ucbp1.features.dollar.domain.model

data class DollarModel(
    var dollarOfficial: String? = null,
    var dollarParallel: String? = null,
    val dollarUsdt: String? = null,     // NUEVO
    val dollarUsdc: String? = null,     // NUEVO
    val updatedAt: Long = System.currentTimeMillis()
)
