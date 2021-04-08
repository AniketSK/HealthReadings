package com.aniketkadam.healthreadings.readinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aniketkadam.healthreadings.readings.HealthReading
import com.aniketkadam.healthreadings.readings.HealthReadingDisplay
import kotlin.random.Random

@Composable
fun ReadingList(readings: List<HealthReading>) {

    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(readings) {
            HealthReadingDisplay(it)
        }
    }
}

@Preview
@Composable
fun PreviewReadingsList() {
    val demoList = List(6) {
        HealthReading().apply {
            oxygenation = Random.nextInt(90, 99)
            pulse = Random.nextInt(80, 120)
            temperature = if (it % 2 == 0) null else "%.2f".format(Random.nextFloat() * 7.0f + 90).toFloat()
            respiratoryRate = if (it % 3 == 0) null else Random.nextInt(12, 20)
        }
    }

    ReadingList(demoList)
}