package ru.myworld.products2.dto

internal fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        thumbnail = thumbnail
    )
}