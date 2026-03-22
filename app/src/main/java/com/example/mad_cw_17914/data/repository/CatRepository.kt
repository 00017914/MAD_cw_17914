package com.example.mad_cw_17914.data.repository

import com.example.mad_cw_17914.data.model.Cat
import com.example.mad_cw_17914.data.network.CatApiService
import retrofit2.Response

/**
 * LO3: Data Layer - Repository Pattern
 * This class abstracts the data source (API) from the rest of the app.
 * It ensures the ViewModel doesn't need to know about Retrofit.
 */
class CatRepository(private val apiService: CatApiService) {

    // Requirement 4.2.1: View the list of all products (GET)
    suspend fun getCats() = apiService.getAllCats()

    // Requirement 4.3.1: Create a new product (POST)
    suspend fun insertCat(cat: Cat): Response<Unit> {
        return apiService.addCat(cat)
    }

    // FIXED: Added function body to call the apiService
    suspend fun updateQuantity(id: Int, cat: Cat): Response<Unit> {
        return apiService.updateCat(id.toString(), cat)
    }

    // Requirement 4.5.1: Delete a product (DELETE)
    suspend fun removeCat(id: Int): Response<Unit> {
        return apiService.deleteCat(id)
    }
}