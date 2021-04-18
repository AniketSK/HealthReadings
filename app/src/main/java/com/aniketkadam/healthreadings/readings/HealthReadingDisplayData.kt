package com.aniketkadam.healthreadings.readings

import com.aniketkadam.healthreadings.readinglist.BaseDisplayClass
import java.text.SimpleDateFormat
import java.util.*

data class HealthReadingDisplayData(
    val healthReading: HealthReading,
    val nullNumberRepresentation: String
) : BaseDisplayClass() {

    val displayDate: String =
        SimpleDateFormat("hh:mm dd MMM", Locale.getDefault()).format(healthReading.date)
    val displayDateTime: String =
        SimpleDateFormat("hh:mm", Locale.getDefault()).format(healthReading.date)
    val displayDateDayMonth: String =
        SimpleDateFormat("dd MMM", Locale.getDefault()).format(healthReading.date)

    val date = healthReading.date
    val temperature: String = numberFormatter(healthReading.temperature)
    val respiratoryRate: String = numberFormatter(healthReading.respiratoryRate)
    val oxygenation: String = numberFormatter(healthReading.oxygenation)
    val pulse: String = numberFormatter(healthReading.pulse)
    val userId: String = healthReading.userId

    private fun numberFormatter(n: Number?): String {
        return if (n == null) {
            nullNumberRepresentation
        } else {
            n.toString()
        }
    }

}