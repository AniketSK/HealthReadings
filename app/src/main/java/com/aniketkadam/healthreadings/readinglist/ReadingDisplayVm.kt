package com.aniketkadam.healthreadings.readinglist


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniketkadam.healthreadings.dao.ReadingsDao
import com.aniketkadam.healthreadings.readings.HealthReadingDisplayData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadingDisplayVm @Inject constructor(readingsDao: ReadingsDao) : ViewModel() {

    var readings: List<HealthReadingDisplayData> by mutableStateOf(emptyList())
        private set

    init {
        viewModelScope.launch {
            readingsDao.getAllReadings()
                .map { it.map { HealthReadingDisplayData(it, "-") } }
                .flowOn(Dispatchers.IO)
                .collect {
                    readings = it
                }
        }
    }
}