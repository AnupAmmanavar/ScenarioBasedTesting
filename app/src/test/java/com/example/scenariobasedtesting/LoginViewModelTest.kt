package com.example.scenariobasedtesting

import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginViewModelTest {

    private val loginRepo: LoginRepo by lazy { mockk(relaxed = true) }
    private val loginViewModel = LoginViewModel(loginRepo)

    private val ui: LoginUi by lazy { LoginProxyUi(loginViewModel) }
    private val validation: LoginValidation = LoginValidationImplementation(loginRepo)


    @Test
    fun `When username and password is entered and T&C is unchecked, Login API is not called`() {

        runBlocking {

            with(ui) {
                enterUsername("username")
                enterPassword("password")
                uncheckTAndC()
            }

            with(validation) {
                loginApiIsNotCalled()
            }
        }
    }

    @Test
    fun `When username and password is entered and T&C is checked, Login API is called`() =
        runBlocking {
            with(ui) {
                enterUsername("username")
                enterPassword("password")
                checkTAndC()
                clickLogin()
            }

            with(validation) {
                loginApiIsCalled()
            }
        }

    @Test
    fun `When username entered and password is empty and T&C is checked, Login API is not called`() =
        runBlocking {
            with(ui) {
                enterUsername("username")
                enterPassword("")
                checkTAndC()
                clickLogin()
            }

            with(validation) {
                loginApiIsNotCalled()
            }
        }

    @Test
    fun `When username is entered and password is empty and T&C is checked, Login API is not called`() =
        runBlocking {
            with(ui) {
                enterUsername("username")
                enterPassword("")
                checkTAndC()
                clickLogin()
            }

            with(validation) {
                loginApiIsNotCalled()
            }
        }
}

// Line 1 added
