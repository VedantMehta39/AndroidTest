package com.datechnologies.androidtest.api.chatmessage

import retrofit2.http.GET

interface ChatMessagesAPI {

    @GET("chat_log.php/")
    suspend fun getChatMessages(): ChatMessagesResponse
}