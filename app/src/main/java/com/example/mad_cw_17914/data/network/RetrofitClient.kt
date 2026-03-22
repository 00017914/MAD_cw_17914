package com.example.mad_cw_17914.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Object declaration for Singleton pattern
object RetrofitClient {
    private const val BASE_URL = "https://wiutmadcw.uz/api/v1/"

    val instance: CatApiService by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(CatApiService::class.java)
    }
}