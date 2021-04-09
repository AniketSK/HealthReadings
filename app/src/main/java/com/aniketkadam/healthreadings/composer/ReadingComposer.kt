package com.aniketkadam.healthreadings.composer


import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aniketkadam.healthreadings.readings.HealthReading
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.datetimepicker
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

@Composable
fun ReadingComposer(
    submit: () -> Unit,
    healthReading: MutableState<HealthReading>,
    complete: () -> Unit
) {

    val dialog = remember { MaterialDialog() }
    dialog.build {
        datetimepicker {
            healthReading.value.date = Date.from(it.atZone(ZoneId.systemDefault()).toInstant())
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

            OutlinedTextField(
                value = healthReading.value.oxygenation.toString(),
                onValueChange = { healthReading.value.oxygenation = it.toIntOrNull() },
                label = { Text("Oxygenation") },
                leadingIcon = { Text("🇴") },
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = if (isSystemInDarkTheme()) Color.White else Color.Black)
            )

            OutlinedTextField(
                value = healthReading.value.pulse.toString(),
                onValueChange = { healthReading.value.pulse = it.toIntOrNull() },
                label = { Text("Pulse") },
                leadingIcon = { Text("💓") },
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = if (isSystemInDarkTheme()) Color.White else Color.Black)
            )

            OutlinedTextField(
                value = healthReading.value.temperature.toString(),
                onValueChange = { healthReading.value.temperature = it.toFloatOrNull() },
                label = { Text("Temperature") },
                leadingIcon = { Text("🌡️") },
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = if (isSystemInDarkTheme()) Color.White else Color.Black)
            )

            OutlinedTextField(
                value = healthReading.value.respiratoryRate.toString(),
                onValueChange = { healthReading.value.respiratoryRate = it.toIntOrNull() },
                label = { Text("Respiratory Rate") },
                leadingIcon = { Text("😮‍💨") },
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = if (isSystemInDarkTheme()) Color.White else Color.Black)
            )

            Text(
                "At ${
                    SimpleDateFormat(
                        "hh:mm E dd/MMM/yy",
                        Locale.getDefault()
                    ).format(healthReading.value.date)
                }", Modifier.clickable {
                    dialog.show()
                })

            Button(
                {
                    submit()
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewReadingComposer() {
//    ReadingComposer({}) {}
//}