package com.aniketkadam.healthreadings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.aniketkadam.healthreadings.composer.ComposerVm
import com.aniketkadam.healthreadings.composer.ReadingComposer
import com.aniketkadam.healthreadings.login.Login
import com.aniketkadam.healthreadings.login.LoginVm
import com.aniketkadam.healthreadings.readinglist.ReadingDisplayVm
import com.aniketkadam.healthreadings.readinglist.ReadingList
import com.aniketkadam.healthreadings.ui.theme.HealthReadingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthReadingsTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "login") {

                    composable("login") {
                        val vm by viewModels<LoginVm>()
                        Login(vm::login, vm.username, vm.password, vm.networkResult) {
                            navController.navigate("readingList")
                        }
                    }

                    composable("readingList") {
                        val vm by viewModels<ReadingDisplayVm>()
                        ReadingList(vm.readings) {
                            navController.navigate("composeReading")
                        }
                    }

                    composable("composeReading") {
                        val vm by viewModels<ComposerVm>()
                        ReadingComposer(vm::submitReading) {
                            navController.navigate("readingList")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HealthReadingsTheme {
        Greeting("Android")
    }
}