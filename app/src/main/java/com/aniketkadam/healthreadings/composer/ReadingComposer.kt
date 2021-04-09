package com.aniketkadam.healthreadings.composer


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aniketkadam.healthreadings.readings.HealthReading
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.datetimepicker
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

@Composable
fun ReadingComposer(
    submit: (HealthReading) -> Unit,
    healthReading: HealthReading? = null,
    complete: () -> Unit
) {

    var oxygenation by rememberSaveable {
        mutableStateOf(
            healthReading?.oxygenation?.toString() ?: ""
        )
    }
    var pulse by rememberSaveable { mutableStateOf(healthReading?.pulse?.toString() ?: "") }
    var temperature by rememberSaveable {
        mutableStateOf(
            healthReading?.temperature?.toString() ?: ""
        )
    }
    var respiratoryRate by rememberSaveable {
        mutableStateOf(
            healthReading?.respiratoryRate?.toString() ?: ""
        )
    }

    var savedDate by rememberSaveable {
        mutableStateOf(healthReading?.date ?: Date())
    }

    val dialog = remember { MaterialDialog() }
    dialog.build {
        datetimepicker {
            savedDate = Date.from(it.atZone(ZoneId.systemDefault()).toInstant())
        }
    }
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

            Text(
                "At ${
                    SimpleDateFormat(
                        "hh:mm E dd/MMM/yy",
                        Locale.getDefault()
                    ).format(savedDate)
                }", Modifier.clickable {
                    dialog.show()
                })

            Button(
                {
                    submit(HealthReading().apply {
                        if (healthReading != null) { // This would make it an update if it needed to be
                            this._id = healthReading._id
                        }
                        this.temperature = temperature.toFloatOrNull()
                        this.respiratoryRate = respiratoryRate.toIntOrNull()
                        this.pulse = pulse.toIntOrNull()
                        this.oxygenation = oxygenation.toIntOrNull()
                    })
                    complete()
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
    ReadingComposer({}) {}
}