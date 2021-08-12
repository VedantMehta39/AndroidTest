package com.datechnologies.androidtest.login

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

interface LoginRepo {
    fun login(username: String, password: String): Result<Boolean, LoginError>
}

object SuccesfulLoginRepo : LoginRepo {
    override fun login(username: String, password: String): Result<Boolean, LoginError> = Ok(true)

}

abstract class LoginError()