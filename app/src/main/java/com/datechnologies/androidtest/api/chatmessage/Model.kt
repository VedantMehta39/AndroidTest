package com.datechnologies.androidtest.api.chatmessage

import com.google.gson.annotations.SerializedName

data class ChatMessagesResponse(val data: List<ChatLogMessageModel>)
data class ChatLogMessageModel(
    @SerializedName("user_id") val userId: Int = 0,
    @SerializedName("avatar_url") val avatarUrl: String? = null,
    @SerializedName("name") val username: String? = null,
    @SerializedName("message") val message: String? = null
)