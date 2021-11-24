package com.example.hackathon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hackathon.domain.request.LoginRequest
import com.example.hackathon.domain.response.AuthToken
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    BaseViewModel() {
    private val TAG = "OwnerLoginViewModel"

    val loginState = MutableLiveData<DataState<AuthToken>>()

    fun loginClient(email: String, pw: String) {
        job = viewModelScope.launch {
            loginState.postValue(DataState.Loading)
            loginRepository.loginClient(LoginRequest(email, pw))
                .catch {
                    loginState.postValue(DataState.Failure(500, "서버 연결에 실패했어요"))
                    Log.d(TAG, it.message.toString())
                }
                .collect {
                    when (it.code) {
                        200 -> loginState.postValue(DataState.Success(it.data))
                        in 400..499 -> loginState.postValue(DataState.Failure(it.code, it.message))
                    }
                }
        }
    }

    fun loginOwner(email: String, pw: String) {
        job = viewModelScope.launch {
            loginState.postValue(DataState.Loading)
            loginRepository.loginOwner(LoginRequest(email, pw))
                .catch {
                    loginState.postValue(DataState.Failure(500, "서버 연결에 실패했어요"))
                    Log.d(TAG, it.message.toString())
                }
                .collect {
                    when (it.code) {
                        200 -> loginState.postValue(DataState.Success(it.data))
                        in 400..499 -> loginState.postValue(DataState.Failure(it.code, it.message))
                    }
                }
        }
    }


}