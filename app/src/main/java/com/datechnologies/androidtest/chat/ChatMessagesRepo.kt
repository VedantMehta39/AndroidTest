package com.datechnologies.androidtest.chat

import com.datechnologies.androidtest.api.chatmessage.ChatLogMessageModel
import com.datechnologies.androidtest.api.chatmessage.ChatMessagesAPI
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.koin.java.KoinJavaComponent.inject

interface ChatMessagesRepo {
    suspend fun getAllChatMessages(): Result<List<ChatLogMessageModel>, ChatMessagesError>
}

object SuccessfulChatMessagesRepo : ChatMessagesRepo {
    override suspend fun getAllChatMessages(): Result<List<ChatLogMessageModel>, ChatMessagesError> =
        Ok(emptyList())
}

class RealChatMessagesRepo(
    private val api: ChatMessagesAPI = inject<ChatMessagesAPI>(
        ChatMessagesAPI::class.java
    ).value
) : ChatMessagesRepo {

    override suspend fun getAllChatMessages(): Result<List<ChatLogMessageModel>, ChatMessagesError> =
        try {
            Ok(api.getChatMessages().data)
        } catch (e: retrofit2.HttpException) {
            when (e.code()) {
                404 -> Err(NoChatMessagesFound)
                else -> Err(UnknownError)
            }
        }

}

abstract class ChatMessagesError()
object NoChatMessagesFound : ChatMessagesError()
object UnknownError : ChatMessagesError()