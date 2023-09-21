package com.store.cbapp.model

data class Product(
    val sku: String,
    val name: String,
    val brand: Brand,
    val price: Double? =null,
    val quantity: Int? = null
)
