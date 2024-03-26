package com.example.scenariobasedtesting

import io.mockk.verify

interface LoginValidation {
    fun loginApiIsCalled()
    fun loginApiIsNotCalled()
}

class LoginValidationImplementation(
    private val loginRepo: LoginRepo
) : LoginValidation {
    override fun loginApiIsCalled() {
        verify(exactly = 1) { loginRepo.loginClick(any(), any(), any()) }
    }

    override fun loginApiIsNotCalled() {
        verify(exactly = 0) { loginRepo.loginClick(any(), any(), any()) }
    }
}