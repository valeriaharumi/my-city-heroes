package com.github.valeriaharumi.heroes_mobile.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:5087/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(Api::class.java)

    suspend fun getCategories(): List<String> {
        return api.getCategories()
    }

    suspend fun submitReport(report: Report) {
        return api.submitReport(report)
    }

    interface Api {
        @GET("Categories")
        suspend fun getCategories(): List<String>

        @POST("ProblemReport")
        suspend fun submitReport(@Body report: Report)
    }
}