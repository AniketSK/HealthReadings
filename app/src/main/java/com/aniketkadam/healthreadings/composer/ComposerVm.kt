package com.aniketkadam.healthreadings.composer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniketkadam.healthreadings.dao.ReadingsDao
import com.aniketkadam.healthreadings.readings.HealthReading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComposerVm @Inject constructor(private val dao: ReadingsDao) : ViewModel() {

    var currentHealthReading = mutableStateOf<HealthReading?>(null)
        private set

    fun setCurrentHealthReadingId(id: String) {
        viewModelScope.launch {
            currentHealthReading.value = dao.getReading(id) ?: HealthReading()
        }
    }

    fun submitReading(healthReading: HealthReading) {
        dao.submitReading(healthReading)
        currentHealthReading.value = null
    }

}