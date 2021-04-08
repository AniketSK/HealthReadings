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

    var oxygenation by rememberSaveable { mutableStateOf("") }
    var pulse by rememberSaveable { mutableStateOf("") }
    var temperature by rememberSaveable { mutableStateOf("") }
    var respiratoryRate by rememberSaveable { mutableStateOf("") }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
        ) {

            OutlinedTextField(value = oxygenation,
                onValueChange = { oxygenation = it },
                label = { Text("Oxygenation") },
                leadingIcon = { Text("🇴") })

            OutlinedTextField(value = pulse,
                onValueChange = { pulse = it },
                label = { Text("Pulse") },
                leadingIcon = { Text("💓") })

            OutlinedTextField(value = temperature,
                onValueChange = { temperature = it },
                label = { Text("Temperature") },
                leadingIcon = { Text("🌡️") })

            OutlinedTextField(value = respiratoryRate,
                onValueChange = { respiratoryRate = it },
                label = { Text("Respiratory Rate") },
                leadingIcon = { Text("😮‍💨") })

            Button(
                {
                    submit(HealthReading().apply {
                        this.temperature = temperature.toFloatOrNull()
                        this.respiratoryRate = respiratoryRate.toIntOrNull()
                        this.pulse = pulse.toIntOrNull()
                        this.oxygenation = oxygenation.toIntOrNull()
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