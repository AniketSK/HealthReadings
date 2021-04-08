package com.aniketkadam.healthreadings


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.aniketkadam.healthreadings.readings.HealthReading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReadingDisplayVm @Inject constructor() : ViewModel(){

    var readings : List<HealthReading> by mutableStateOf(emptyList())
        private set
}