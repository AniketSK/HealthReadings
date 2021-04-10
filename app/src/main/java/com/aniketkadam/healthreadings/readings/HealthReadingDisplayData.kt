package com.aniketkadam.healthreadings.readings

import java.text.SimpleDateFormat
import java.util.*

data class HealthReadingDisplayData(
    val healthReading: HealthReading,
    val nullNumberRepresentation: String
) {
    val displayDate: String =
        SimpleDateFormat("hh:mm\ndd MMM", Locale.getDefault()).format(healthReading.date)
    val date = healthReading.date
    val temperature: String = numberFormatter(healthReading.temperature)
    val respiratoryRate: String = numberFormatter(healthReading.respiratoryRate)
    val oxygenation: String = numberFormatter(healthReading.oxygenation)
    val pulse: String = numberFormatter(healthReading.pulse)
    val userId: String = healthReading.userId

    init {
        check(Thread.currentThread().name != "main") { "Don't do this conversion on the main thread" }
    }

    private fun numberFormatter(n: Number?): String {
        return if (n == null) {
            nullNumberRepresentation
        } else {
            n.toString()
        }
    }

}