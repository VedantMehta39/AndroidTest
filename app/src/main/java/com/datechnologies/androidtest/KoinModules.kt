package com.datechnologies.androidtest
import com.datechnologies.androidtest.login.SuccesfulLoginRepo
import org.koin.dsl.module

val loginModule = module{
    single {
        SuccesfulLoginRepo
    }
}