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

    var currentHealthReading = mutableStateOf(HealthReading())

    fun setCurrentHealthReadingId(id: String) {
        viewModelScope.launch {
            val existingReading = dao.getReading(id)
            if (existingReading != null) {
                currentHealthReading.value = existingReading
            }
        }
    }

    fun submitReading() = dao.submitReading(currentHealthReading.value)

}