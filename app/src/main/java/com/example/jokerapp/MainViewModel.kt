package com.example.jokerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jokerapp.api.JokeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: JokeRepository
) : ViewModel(){

    private val mutableJoke = MutableLiveData<JokeResponse>()
    val joke : LiveData<JokeResponse>
        get() = mutableJoke

    fun getRandomJoke() = viewModelScope.launch {
        mutableJoke.value = repository.getRandomJoke()
    }
}