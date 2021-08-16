package com.datechnologies.androidtest

import com.datechnologies.androidtest.api.chatmessage.ChatMessagesAPI
import com.datechnologies.androidtest.chat.ChatMessagesRepo
import com.datechnologies.androidtest.chat.RealChatMessagesRepo
import com.datechnologies.androidtest.api.login.LoginAPI
import com.datechnologies.androidtest.login.LoginRepo
import com.datechnologies.androidtest.login.RealLoginRepo
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val loginModule = module {
    single<LoginRepo> {
        RealLoginRepo()
    }
    single<LoginAPI> {
        Retrofit.Builder()
            .baseUrl(MyApplication.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LoginAPI::class.java)
    }
}

val chatModule = module {
    single<ChatMessagesRepo> {
        RealChatMessagesRepo()
    }
    single<ChatMessagesAPI> {
        Retrofit.Builder()
            .baseUrl(MyApplication.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ChatMessagesAPI::class.java)
    }
}