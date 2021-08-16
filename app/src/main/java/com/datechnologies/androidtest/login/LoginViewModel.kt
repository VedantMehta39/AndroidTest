package com.datechnologies.androidtest.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.datechnologies.androidtest.api.login.LoginDialogModel
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import org.koin.java.KoinJavaComponent.inject
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class LoginViewModel : ViewModel() {
    private val repo: LoginRepo by inject(LoginRepo::class.java)

    var email: String = ""
    var password: String = ""

    @ExperimentalTime
    fun login() = liveData<LoginDialogModel> {
        val result = measureTimedValue {
            repo.login(email, password)
        }
        emit(
            when(val response = result.value){
                is Ok -> LoginDialogModel(response.value.code, response.value.message, result.duration.inWholeMilliseconds)
                is Err -> LoginDialogModel("Error", response.error.toString(), result.duration.inWholeMilliseconds)
            }
        )
    }
}