package com.example.scenariobasedtesting

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * This is a login view model that maintains the state of username, passwork and T&CCheckbox
 */

interface Reducer<S> {
    val stateFlow: Flow<S>
    val state: S
}

open class StateReducer<S>(initialState: S): Reducer<S> {

    private val mutableStateFlow = MutableStateFlow(initialState)
    override val stateFlow: StateFlow<S> = mutableStateFlow.asStateFlow()
    override val state: S
        get() = stateFlow.value


    fun updateState(reducer: S.() -> S) {
        mutableStateFlow.value = state.reducer()
    }
}
