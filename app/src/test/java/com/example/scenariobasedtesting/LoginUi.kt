package com.example.scenariobasedtesting

interface LoginUi {
    fun enterUsername(username: String)
    fun enterPassword(password: String)
    fun checkTAndC()
    fun uncheckTAndC()
    fun clickLogin()
}

class LoginProxyUi(
    private val eventConsumer: LoginEventConsumer
) : LoginUi {
    override fun enterUsername(username: String) {
        eventConsumer.onEvent(LoginEvent.UsernameChanged(username))
    }

    override fun enterPassword(password: String) {
        eventConsumer.onEvent(LoginEvent.PasswordChanged(password))
    }

    override fun checkTAndC() {
        eventConsumer.onEvent(LoginEvent.TAndCChecked(true))
    }

    override fun uncheckTAndC() {
        eventConsumer.onEvent(LoginEvent.TAndCChecked(false))
    }

    override fun clickLogin() {
        eventConsumer.onEvent(LoginEvent.LoginClicked)
    }
}