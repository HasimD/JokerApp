package com.example.jokerapp.api

data class JokeResponse(
    val id: Int,
    val type : String,
    val setup: String,
    val punchline: String
)
