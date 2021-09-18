package one.valuelogic.moshi.adapter

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Employee(
    val name: String,
    val id: Int
)