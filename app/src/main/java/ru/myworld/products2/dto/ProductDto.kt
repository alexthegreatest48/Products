package ru.myworld.products2.dto


data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val thumbnail: String
)