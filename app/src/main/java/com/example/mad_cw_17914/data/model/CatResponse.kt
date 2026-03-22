package com.example.mad_cw_17914.data.model

data class CatResponse(
    val code: Int,
    val status: String,
    val message: String,
    val data: List<Cat> // This is where your cats actually live
)