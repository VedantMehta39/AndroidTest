package com.datechnologies.androidtest

import android.app.Application
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Picasso.get().isLoggingEnabled = true
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    loginModule,
                    chatModule
                )
            )
        }
    }

    companion object {
        const val BASE_URL = "https://dev.rapptrlabs.com/Tests/scripts/"
    }
}