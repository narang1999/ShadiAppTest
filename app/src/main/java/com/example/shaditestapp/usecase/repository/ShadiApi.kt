package com.example.shaditestapp.usecase.repository


import com.example.shaditestapp.model.DataResponse
import com.example.shaditestapp.model.Results
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface ShadiApi {

    @GET("api/")
    fun getShadiMatchesUsers(
        @Query("results") results: Int = 10,
        ): Maybe<DataResponse<List<Results>>>}