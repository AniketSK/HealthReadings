package com.aniketkadam.healthreadings.realminit

sealed class InitializationState {
    object WAITING : InitializationState()
    object SUCCESS : InitializationState()
    object LOADING : InitializationState()
    data class ERROR(val message: String) : InitializationState()
}