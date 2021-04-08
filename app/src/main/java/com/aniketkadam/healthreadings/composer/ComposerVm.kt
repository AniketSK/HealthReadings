package com.aniketkadam.healthreadings.composer

import androidx.lifecycle.ViewModel
import com.aniketkadam.healthreadings.dao.ReadingsDao
import com.aniketkadam.healthreadings.readings.HealthReading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComposerVm @Inject constructor(private val dao: ReadingsDao) : ViewModel() {

    fun submitReading(healthReading: HealthReading) = dao.submitReading(healthReading)
}