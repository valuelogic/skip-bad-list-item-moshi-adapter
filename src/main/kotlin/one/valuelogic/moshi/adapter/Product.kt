package one.valuelogic.moshi.adapter

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    val name: String,
    val price: Double
)