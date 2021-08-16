package com.datechnologies.androidtest.login

import com.datechnologies.androidtest.api.login.LoginAPI
import com.datechnologies.androidtest.api.login.LoginResponse
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertIs

class LoginRepoTest {

    companion object {
        const val TEST_USERNAME = "TEST"
        const val TEST_PASSWORD = "TEST"
    }

    @Test
    fun `Login Unauthorized`() {
        val mockException = mockk<retrofit2.HttpException>().apply {
            every { code() } returns 401
        }
        val api = mockk<LoginAPI>().apply {
            coEvery {
                login(
                    TEST_USERNAME,
                    TEST_PASSWORD
                )
            } throws mockException
        }
        val repo = RealLoginRepo(api)
        runBlocking{
            val result = repo.login(TEST_USERNAME, TEST_PASSWORD)
            assertIs<Err<UnauthorizedLogin>>(result)
        }

    }

    @Test
    fun `HTTP Error`() {
        val mockException = mockk<retrofit2.HttpException>().apply {
            every { code() } returns 503
        }
        val api = mockk<LoginAPI>().apply {
            coEvery {
                login(
                    TEST_USERNAME,
                    TEST_PASSWORD
                )
            } throws mockException
        }
        val repo = RealLoginRepo(api)
        runBlocking{
            val result = repo.login(TEST_USERNAME, TEST_PASSWORD)
            assertIs<Err<UnknownError>>(result)
        }

    }

    @Test
    fun `Random Client Error`(){
        val api = mockk<LoginAPI>().apply {
            coEvery {
                login(
                    TEST_USERNAME,
                    TEST_PASSWORD
                )
            } throws OutOfMemoryError()
        }
        val repo = RealLoginRepo(api)
        runBlocking{
            assertFails {
                repo.login(TEST_USERNAME, TEST_PASSWORD)
            }
        }
    }


    @Test
    fun `Successful Login`(){
        val api = mockk<LoginAPI>().apply {
            coEvery {
                login(
                    TEST_USERNAME,
                    TEST_PASSWORD
                )
            } returns LoginResponse("Success", "Login Successful!")
        }
        val repo = RealLoginRepo(api)
        runBlocking{
            assertIs<Ok<LoginResponse>>(repo.login(TEST_USERNAME, TEST_PASSWORD))
        }
    }
}