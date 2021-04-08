package com.aniketkadam.healthreadings.readings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HealthReadingDisplay(reading: HealthReading, onClick: (HealthReading) -> Unit) {
    Card(
        Modifier
            .width(84.dp)
            .clickable(onClick = { onClick(reading) }, onClickLabel = "Edit", role = Role.Button)
            .padding(4.dp)
    ) {
        Column(Modifier.padding(4.dp)) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            ) {
                Text(
                    SimpleDateFormat("hh:mm", Locale.getDefault()).format(reading.date),
                    color = Color.DarkGray
                )
            }
            Row {
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
}

@Preview
@Composable
fun PreviewHealthReadingWithAllValues() {
    HealthReadingDisplay(reading = HealthReading().apply {
        oxygenation = 99
        pulse = 80
        temperature = 97.5f
        respiratoryRate = 18
    }) {}
}

@Preview
@Composable
fun PreviewHealthReadingWithSomeValuesMissing() {
    HealthReadingDisplay(reading = HealthReading().apply {
        oxygenation = 99
        pulse = 80
    }) {}
}