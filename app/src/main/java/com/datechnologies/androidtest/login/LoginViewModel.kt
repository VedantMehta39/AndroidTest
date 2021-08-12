package com.datechnologies.androidtest.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.michaelbull.result.Result
import org.koin.java.KoinJavaComponent.inject

class LoginViewModel : ViewModel() {
    lateinit var repo: LoginRepo

    fun login(username: String, password: String) = liveData<Result<Boolean, LoginError>> {
        repo.login(username, password)
    }
}