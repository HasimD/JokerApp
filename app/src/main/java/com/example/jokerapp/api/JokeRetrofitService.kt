package com.example.jokerapp.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface JokeRetrofitService {

    @GET("random_joke")
    suspend fun getRandomJoke() : Response<JokeResponse>
}