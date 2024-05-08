package ru.myworld.products2.dto

data class Product (
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val brand: String,
    val thumbnail: String
)