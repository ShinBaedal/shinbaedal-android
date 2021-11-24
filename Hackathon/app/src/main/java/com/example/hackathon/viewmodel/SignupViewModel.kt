package com.example.hackathon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hackathon.domain.request.SignupRequest
import com.example.hackathon.domain.response.AuthToken
import com.example.hackathon.domain.response.DataState
import com.example.hackathon.data.repository.SignupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val signupRepository: SignupRepository) :
    BaseViewModel() {
    private val TAG = "SignupViewModel"
    val ownerSignupState = MutableLiveData<DataState<AuthToken>>()
    val clientSignupState = MutableLiveData<DataState<AuthToken>>()
    val emailAuthState = MutableLiveData<DataState<Unit>>()


    fun signupClient(email: String, pw: String, name: String) {
        job = viewModelScope.launch {
            signupRepository.signUpClient(SignupRequest(email, pw, name))
                .catch {
                    clientSignupState.postValue(DataState.Failure(500, "서버 연결에 실패했어요"))
                    Log.d(TAG, it.message.toString())
                }
                .collect {
                    when (it.code) {
                        200 -> clientSignupState.postValue(DataState.Success(it.data))
                        in 400..499 -> clientSignupState.postValue(
                            DataState.Failure(it.code, it.message)
                        )

                    }
                }
        }
    }

    fun signupOwner(email: String, pw: String, name: String) {
        job = viewModelScope.launch {
            signupRepository.signUpOwner(SignupRequest(email, pw, name))
                .catch {
                    ownerSignupState.postValue(DataState.Failure(500, "서버 연결에 실패했어요"))
                    Log.d(TAG, it.message.toString())
                }
                .collect {
                    when (it.code) {
                        200 -> ownerSignupState.postValue(DataState.Success(it.data))
                        in 400..499 -> ownerSignupState.postValue(
                            DataState.Failure(it.code, it.message)
                        )
                    }
                }
        }
    }

    fun requestEmailAuth(email: String) {
        job = viewModelScope.launch {
            signupRepository.requestEmailAuth(email)
                .catch {
                    emailAuthState.postValue(DataState.Failure(500, "서버 연결에 실패했어요"))
                }
                .collect {
                    when (it.code) {
                        200 -> emailAuthState.postValue(DataState.Loading)
                        409 -> emailAuthState.postValue(DataState.Failure(it.code, it.message))
                    }
                }
        }
    }

    fun checkCode(code: Int) {
        job = viewModelScope.launch {
            signupRepository.checkCode(code)
                .catch {
                    Log.d(TAG, it.message.toString())
                }
                .collect {
                    when (it.code) {
                        200 -> emailAuthState.postValue(DataState.Success(Unit))
                        in 400..499 -> emailAuthState.postValue(
                            DataState.Failure(400, "코드를 잘 입력했는지 확인해주세요")
                        )
                    }
                }
        }
    }
}