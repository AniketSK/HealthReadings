package com.aniketkadam.healthreadings.composer

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniketkadam.healthreadings.dao.ReadingsDao
import com.aniketkadam.healthreadings.readings.HealthReading
import kotlinx.coroutines.launch

class ComposerVm(private val dao: ReadingsDao, existingReading: String?) :
    ViewModel() {

    var currentHealthReading = mutableStateOf<HealthReading?>(null)
        private set

    init {
        Log.d("Creating", "Initing")
        viewModelScope.launch {

            // Get either the reading that exists or create a new one.
            //  Existing Id or the reading for it may be null
            currentHealthReading.value =
                existingReading?.let { dao.getReading(it) } ?: HealthReading()

        }
    }

    fun submitReading(healthReading: HealthReading) {
        dao.submitReading(healthReading)
        currentHealthReading.value = null
    }

    override fun onCleared() {
        Log.d("Creating", "Cleared")
        super.onCleared()
    }
}