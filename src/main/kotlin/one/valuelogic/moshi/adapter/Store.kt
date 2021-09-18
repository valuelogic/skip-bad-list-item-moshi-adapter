package one.valuelogic.moshi.adapter

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Store(
    val name: String,
    val products: List<Product>
)