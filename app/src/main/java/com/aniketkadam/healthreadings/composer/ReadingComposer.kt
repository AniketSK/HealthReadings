package com.aniketkadam.healthreadings.composer


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aniketkadam.healthreadings.readings.HealthReading
import com.aniketkadam.healthreadings.timer.TimerDisplay
import com.aniketkadam.healthreadings.timer.TimerVm
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.datetimepicker
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*


@Composable
fun ReadingComposer(
    submit: (HealthReading) -> Unit,
    initialHealthReading: MutableState<HealthReading?>,
    complete: () -> Unit
) {

    when (val healthReading = initialHealthReading.value) {
        null -> CircularProgressIndicator()
        else -> {

            var oxygenation by rememberSaveable(healthReading) {
                mutableStateOf(healthReading.oxygenation?.toString() ?: "")
            }
            var pulse by rememberSaveable(healthReading) {
                mutableStateOf(
                    healthReading.pulse?.toString() ?: ""
                )
            }
            var temperature by rememberSaveable(healthReading) {
                mutableStateOf(
                    healthReading.temperature?.toString() ?: ""
                )
            }
            var respiratoryRate by rememberSaveable(healthReading) {
                mutableStateOf(
                    healthReading.respiratoryRate?.toString() ?: ""
                )
            }

            var savedDate by rememberSaveable(healthReading) {
                mutableStateOf(healthReading.date)
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

                    OutlinedTextField(
                        value = oxygenation,
                        onValueChange = { oxygenation = it },
                        label = { Text("Oxygenation") },
                        leadingIcon = { Text("????") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = if (isSystemInDarkTheme()) Color.White else Color.Black)
                    )

                    OutlinedTextField(
                        value = pulse,
                        onValueChange = { pulse = it },
                        label = { Text("Pulse") },
                        leadingIcon = { Text("????") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = if (isSystemInDarkTheme()) Color.White else Color.Black)
                    )

                    OutlinedTextField(
                        value = temperature,
                        onValueChange = { temperature = it },
                        label = { Text("Temperature") },
                        leadingIcon = { Text("???????") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = if (isSystemInDarkTheme()) Color.White else Color.Black)
                    )

                    OutlinedTextField(
                        value = respiratoryRate,
                        onValueChange = { respiratoryRate = it },
                        label = { Text("Respiratory Rate") },
                        leadingIcon = { Text("???????????") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = if (isSystemInDarkTheme()) Color.White else Color.Black),
                        trailingIcon = {
                            val vm = viewModel<TimerVm>()
                            val timerState = vm.timerStateFlow.collectAsState()
                            TimerDisplay(timerState.value, true, vm::toggleStart)
                        }
                    )

                    Text(
                        "At ${
                            SimpleDateFormat(
                                "hh:mm E dd/MMM/yy",
                                Locale.getDefault()
                            ).format(Date())
                        }",
                        Modifier
                            .clickable {
                                dialog.show()
                            }
                            .padding(4.dp),
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )

                    Button(
                        {
                            submit(HealthReading().apply {
                                _id = healthReading._id
                                this.oxygenation = oxygenation.toIntOrNull()
                                this.temperature = temperature.toFloatOrNull()
                                this.pulse = pulse.toIntOrNull()
                                this.respiratoryRate = respiratoryRate.toIntOrNull()
                                this.date = savedDate
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
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewReadingComposer() {
    ReadingComposer(submit = { }, initialHealthReading = mutableStateOf(HealthReading())) {}
}