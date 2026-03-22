package com.example.mad_cw_17914.data.model

import com.google.gson.annotations.SerializedName

data class Cat(
    val id: Int = 0,
    @SerializedName("title") val name: String,
    @SerializedName("description") val breed: String,
    @SerializedName("age") val age: Int, // API returns text
    @SerializedName("type") val gender: String,
    @SerializedName("is_it_true") val isVaccinated: Boolean, // API returns "1" or "0"
    @SerializedName("phone") val bio: String,
    @SerializedName("size") val stockQuantity: Int // API returns text
)