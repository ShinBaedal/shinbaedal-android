package com.example.hackathon.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    protected var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}