package com.aniketkadam.healthreadings.readings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HealthReadingDisplay(reading: HealthReading) {
    Card(Modifier.width(84.dp)) {
        Row(modifier = Modifier.padding(4.dp)){
            Column(Modifier.padding(end = 4.dp)) {
                Text("🇴 ")
                Text("💓 ")
                Text("🌡️ ")
                Text("😮‍💨")
            }
            Column {
                Text("${reading.oxygenation ?: " - "}")
                Text("${reading.pulse ?: " - "}")
                Text("${reading.temperature ?: " - "}")
                Text("${reading.respiratoryRate ?: " - "}")
            }
        }
    }
}

@Preview
@Composable
fun PreviewHealthReadingWithAllValues(){
    HealthReadingDisplay(reading = HealthReading().apply {
        oxygenation = 99
        pulse = 80
        temperature = 97.5f
        respiratoryRate = 18
    })
}

@Preview
@Composable
fun PreviewHealthReadingWithSomeValuesMissing(){
    HealthReadingDisplay(reading = HealthReading().apply {
        oxygenation = 99
        pulse = 80
    })
}