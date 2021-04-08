package com.aniketkadam.healthreadings.login


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniketkadam.healthreadings.realminit.InitializationState
import com.aniketkadam.healthreadings.realminit.SyncedRealmHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVm @Inject constructor(private val syncedRealmHelper: SyncedRealmHelper) : ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var networkResult by mutableStateOf(if (syncedRealmHelper.isLoggedIn) InitializationState.SUCCESS else InitializationState.WAITING)
        private set

    fun login() {
        networkResult = InitializationState.LOADING
        viewModelScope.launch {
            networkResult = syncedRealmHelper.init(username.value, password.value)
        }
    }
}