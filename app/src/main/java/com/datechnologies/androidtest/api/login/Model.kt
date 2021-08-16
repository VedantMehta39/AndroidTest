package com.datechnologies.androidtest.api.login

data class LoginResponse(val code: String, val message: String)

data class LoginDialogModel(val code: String, val message: String, val responseTime: Long)