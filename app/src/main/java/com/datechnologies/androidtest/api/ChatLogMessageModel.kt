package com.datechnologies.androidtest.api

data class ChatLogMessageModel(val userId: Int = 0,
                               val avatarUrl: String? = null,
                               val username: String? = null,
                               val message: String? = null)