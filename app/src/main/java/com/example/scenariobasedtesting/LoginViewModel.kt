package com.example.scenariobasedtesting


interface LoginEventConsumer {
    fun onEvent(event: LoginEvent)
}

sealed class LoginEvent {
    data class UsernameChanged(val username: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class TAndCChecked(val isChecked: Boolean) : LoginEvent()
    data object LoginClicked : LoginEvent()
}


class LoginViewModel(
    private val repo: LoginRepo
) : LoginEventConsumer {

    private val reducer: LoginStateReducer = LoginStateReducer(LoginState.initialState())
    val currState: LoginState
        get() = reducer.state

    override fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.UsernameChanged -> reducer.updateUsername(event.username)
            is LoginEvent.PasswordChanged -> reducer.updatePassword(event.password)
            is LoginEvent.TAndCChecked -> reducer.updateTAndCChecked(event.isChecked)
            LoginEvent.LoginClicked -> LoginClickHandler().onLoginClick()
        }
    }

    private inner class LoginClickHandler {
        fun onLoginClick() {
            val username = currState.username.takeIf { it.isNotBlank() } ?: return
            val password = currState.password.takeIf { it.isNotBlank() } ?: return
            val isTAndCChecked = if (!currState.isTAndCChecked) return else true
            repo.loginClick(username, password, isTAndCChecked)
        }

    }

}

// ViewModel changes
// Conflicting changes
// Another change