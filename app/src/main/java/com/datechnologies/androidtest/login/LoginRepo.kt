package com.datechnologies.androidtest.login

import com.datechnologies.androidtest.api.login.LoginAPI
import com.datechnologies.androidtest.api.login.LoginResponse
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.koin.java.KoinJavaComponent.inject

interface LoginRepo {
    suspend fun login(email: String, password: String): Result<LoginResponse, LoginError>
}

object SuccessfulLoginRepo : LoginRepo {
    override suspend fun login(email: String, password: String): Result<LoginResponse, LoginError> =
        Ok(LoginResponse("Success", "Login Succesful"))
}

class RealLoginRepo(private val api: LoginAPI = inject<LoginAPI>(LoginAPI::class.java).value) :
    LoginRepo {
    override suspend fun login(email: String, password: String): Result<LoginResponse, LoginError> =
        try {
            Ok(api.login(email, password))
        } catch (e: retrofit2.HttpException) {
            when (e.code()) {
                401 -> Err(UnauthorizedLogin("Incorrect email or password"))
                else -> Err(UnknownError)
            }
        }
}

abstract class LoginError(protected val message: String)
class UnauthorizedLogin(message: String) : LoginError(message) {
    override fun toString() = message
}

object UnknownError : LoginError("Unknown error has occured")