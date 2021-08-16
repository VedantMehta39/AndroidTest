package com.datechnologies.androidtest.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datechnologies.androidtest.api.chatmessage.ChatLogMessageModel
import com.datechnologies.androidtest.api.chatmessage.ChatMessagesAPI
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ChatViewModel : ViewModel() {

    val error = MutableLiveData<ChatMessagesError>()
    private val repo: ChatMessagesRepo by inject(ChatMessagesRepo::class.java)
    private val _messages by lazy {
        MutableLiveData<List<ChatLogMessageModel>>()
    }
    val messages: LiveData<List<ChatLogMessageModel>>
    get() = _messages


    fun getAllMessages() {
        viewModelScope.launch {
            when(val result = repo.getAllChatMessages()){
                is Ok -> {
                    val newMessages = result.value.map {
                        ChatLogMessageModel(
                            it.userId,
                            sanitize(it.avatarUrl),
                            it.username,
                            it.message
                        )
                    }
                    _messages.postValue(newMessages)
                }
                is Err -> error.postValue(result.error as ChatMessagesError)
            }
        }
    }

    private fun sanitize(url: String?) = url?.let { "https" + it.split("http")[1] }
}