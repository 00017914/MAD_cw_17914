package com.example.mad_cw_17914.data.network

import com.example.mad_cw_17914.data.model.Cat
import com.example.mad_cw_17914.data.model.CatResponse
import retrofit2.Response
import retrofit2.http.*

interface CatApiService {
    // 4.2.1 View all records
    @GET("records/all")
    suspend fun getAllCats(@Query("student_id") studentId: String = "17914"): CatResponse
    // 4.3.1 Create new record
    @POST("records")
    suspend fun addCat(
        @Body cat: Cat,
        @Query("student_id") studentId: String = "17914"
    ): Response<Unit>

    // 4.4.1 Update record
    @PUT("records/{record_id}")
    suspend fun updateCat(
        @Path("record_id") id: String,
        @Body cat: Cat,
        @Query("student_id") studentId: String = "17914"
    ): Response<Unit>

    // 4.5.1 Delete record
    @DELETE("records/{record_id}")
    suspend fun deleteCat(
        @Path("record_id") id: Int,
        @Query("student_id") studentId: String = "17914"
    ): Response<Unit>
}