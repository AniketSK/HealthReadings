package com.aniketkadam.healthreadings.readinglist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aniketkadam.healthreadings.readings.HealthReading
import com.aniketkadam.healthreadings.readings.HealthReadingDisplay
import com.aniketkadam.healthreadings.readings.HealthReadingDisplayData
import kotlin.random.Random

@ExperimentalFoundationApi
@Composable
fun ReadingList(
    readings: List<HealthReadingDisplayData>,
    onItemClicked: (HealthReading) -> Unit,
    addNewEntry: () -> Unit
) {

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(addNewEntry, Modifier.testTag("launchNewDiaryEntryFab")) {
                Icon(
                    painter = painterResource(id = com.aniketkadam.healthreadings.R.drawable.ic_baseline_add_24),
                    contentDescription = "Add Entry"
                )
            }
        },
    ) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(120.dp)
        ) {
            items(readings) {
                HealthReadingDisplay(it, onItemClicked)
            }
        }
    }

}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewReadingsList() {
    val demoList = List(6) {
        HealthReadingDisplayData(HealthReading().apply {
            oxygenation = Random.nextInt(90, 99)
            pulse = Random.nextInt(80, 120)
            temperature =
                if (it % 2 == 0) null else "%.2f".format(Random.nextFloat() * 7.0f + 90).toFloat()
            respiratoryRate = if (it % 3 == 0) null else Random.nextInt(12, 20)
        }, "")
    }

    ReadingList(demoList, {}) {}
}