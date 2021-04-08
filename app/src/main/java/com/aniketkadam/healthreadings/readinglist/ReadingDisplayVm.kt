package com.aniketkadam.healthreadings.readinglist


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniketkadam.healthreadings.dao.ReadingsDao
import com.aniketkadam.healthreadings.readings.HealthReading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadingDisplayVm @Inject constructor(readingsDao: ReadingsDao) : ViewModel() {

    var readings: List<HealthReading> by mutableStateOf(emptyList())
        private set

    init {
        viewModelScope.launch {
            readingsDao.getAllReadings().collect {
                readings = it
            }
        }
    }
}