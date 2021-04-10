package com.aniketkadam.healthreadings.readings

import org.bson.types.ObjectId
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class HealthReadingDisplayDataTest {

    @Test
    fun `display data shows strings`() {
        val sampleData = HealthReading().apply {
            _id = ObjectId.get()
            temperature = 97.5f
            oxygenation = 99
            pulse = 80
            respiratoryRate = 12
        }

        val testResult = HealthReadingDisplayData(sampleData, "")
        with(testResult) {
            assertThat(temperature, equalTo("97.5"))
            assertThat(oxygenation, equalTo("99"))
            assertThat(pulse, equalTo("80"))
            assertThat(respiratoryRate, equalTo("12"))
        }
    }

    @Test
    fun `display data shows empty strings if the input was null`() {
        val sampleData = HealthReading().apply {
            _id = ObjectId.get()
        }

        val testResult = HealthReadingDisplayData(sampleData, "")

        with(testResult) {
            assertThat(temperature, equalTo(""))
            assertThat(oxygenation, equalTo(""))
            assertThat(pulse, equalTo(""))
            assertThat(respiratoryRate, equalTo(""))
        }
    }

}