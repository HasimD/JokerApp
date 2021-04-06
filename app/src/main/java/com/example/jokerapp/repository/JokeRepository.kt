package com.example.jokerapp.repository

import com.example.jokerapp.api.JokeResponse
import com.example.jokerapp.api.JokeRetrofitService
import javax.inject.Inject

class JokeRepository @Inject constructor(
    private val retrofitService: JokeRetrofitService
) {

    suspend fun getRandomJoke(): JokeResponse {
        val apiResult = retrofitService.getRandomJoke()
        if (apiResult.isSuccessful) {
            val body = apiResult.body()
            if (body != null)
                return body
        } else {
            throw RuntimeException(apiResult.errorBody().toString())
        }
        throw RuntimeException(apiResult.errorBody().toString())
    }
}