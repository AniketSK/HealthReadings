package com.aniketkadam.healthreadings.composer


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aniketkadam.healthreadings.readings.HealthReading

@Composable
fun ReadingComposer(submit: (HealthReading) -> Unit) {

    var oxygenation by rememberSaveable { mutableStateOf<Int?>(null) }
    var pulse by rememberSaveable { mutableStateOf<Int?>(null) }
    var temperature by rememberSaveable { mutableStateOf<Float?>(null) }
    var respiratoryRate by rememberSaveable { mutableStateOf<Int?>(null) }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
        ) {

            OutlinedTextField(value = oxygenation?.toString() ?: "",
                onValueChange = { oxygenation = it.toInt() },
                label = { Text("Oxygenation") },
                leadingIcon = { Text("🇴") })

            OutlinedTextField(value = pulse?.toString() ?: "",
                onValueChange = { pulse = it.toInt() },
                label = { Text("Pulse") },
                leadingIcon = { Text("💓") })

            OutlinedTextField(value = temperature?.toString() ?: "",
                onValueChange = { temperature = it.toFloat() },
                label = { Text("Temperature") },
                leadingIcon = { Text("🌡️") })

            OutlinedTextField(value = respiratoryRate?.toString() ?: "",
                onValueChange = { respiratoryRate = it.toInt() },
                label = { Text("Respiratory Rate") },
                leadingIcon = { Text("😮‍💨") })

            Button(
                {
                    submit(HealthReading().apply {
                        this.temperature = temperature
                        this.respiratoryRate = respiratoryRate
                        this.pulse = pulse
                        this.oxygenation = oxygenation
                    })
                },
                Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
            ) {
                Text("Done")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReadingComposer() {
    ReadingComposer {}
}