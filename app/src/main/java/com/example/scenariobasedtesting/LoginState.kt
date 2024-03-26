package com.example.scenariobasedtesting

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isTAndCChecked: Boolean = false
) {
    companion object {
        fun initialState() = LoginState()
    }
}

data class LoginStateReducer(val initialState: LoginState): StateReducer<LoginState>(initialState) {
    fun updateUsername(username: String) {
        updateState { copy(username = username) }
    }

    fun updatePassword(password: String) {
        updateState { copy(password = password) }
    }

    fun updateTAndCChecked(isChecked: Boolean) {
        updateState { copy(isTAndCChecked = isChecked) }
    }
}