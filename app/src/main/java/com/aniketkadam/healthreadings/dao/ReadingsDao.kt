package com.aniketkadam.healthreadings.dao

import com.aniketkadam.healthreadings.readings.HealthReading
import kotlinx.coroutines.flow.Flow

interface ReadingsDao {
    fun getAllReadings(): Flow<List<HealthReading>>
    fun submitReading(healthReading: HealthReading)
}