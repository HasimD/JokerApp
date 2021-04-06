package com.example.jokerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokerapp.api.JokeResponse
import com.example.jokerapp.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: JokeRepository
) : ViewModel() {

    private val mutableJoke = MutableLiveData<JokeResponse>()
    val joke: LiveData<JokeResponse>
        get() = mutableJoke

    fun getRandomJoke() = viewModelScope.launch {
        mutableJoke.value = repository.getRandomJoke()
    }
}