package com.aniketkadam.healthreadings.timer

import android.os.VibrationEffect
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aniketkadam.healthreadings.composer.SideEffectVibrateOn

@Composable
fun TimerDisplay(timerState: TimerState, vibrateOnStop: Boolean, toggleStartStop: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            timerState.progressPercentage,
            Modifier.clickable { toggleStartStop() })
        Text(timerState.displaySeconds)
    }

    if (vibrateOnStop) {
        SideEffectVibrateOn(
            VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE),
            vibrateIf = {
                timerState.secondsRemaining == 0
            })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTimerDisplay() {
    TimerDisplay(timerState = TimerState(60), vibrateOnStop = false) {}
}