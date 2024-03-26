package com.example.scenariobasedtesting

interface LoginRepo {
    fun loginClick(username: String, password: String, isTAndCChecked: Boolean)
}