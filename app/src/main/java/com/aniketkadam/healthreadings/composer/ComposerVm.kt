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

    // Only supposed to be called when there's a new reading to be created.
    // TODO replace this with a proper taking of the nav args into the viewmodel to make this call itself.
    fun noHealthReadingHack() {
        currentHealthReading.value = HealthReading()
    }

    fun submitReading(healthReading: HealthReading) {
        dao.submitReading(healthReading)
        currentHealthReading.value = null
    }

}