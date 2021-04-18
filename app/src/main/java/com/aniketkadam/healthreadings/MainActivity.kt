package com.aniketkadam.healthreadings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.aniketkadam.healthreadings.composer.ComposerVm
import com.aniketkadam.healthreadings.composer.ReadingComposer
import com.aniketkadam.healthreadings.login.Login
import com.aniketkadam.healthreadings.login.LoginVm
import com.aniketkadam.healthreadings.readinglist.ReadingDisplayVm
import com.aniketkadam.healthreadings.readinglist.ReadingList
import com.aniketkadam.healthreadings.ui.theme.HealthReadingsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var serviceFactory: AssistedComposerVmFactory

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

                        ReadingList(vm.readings, { readingToEdit ->
                            navController.navigate("composeReading?existingEntryId=${readingToEdit._id.toHexString()}")
                        }, {
                            navController.navigate("composeReading")
                        })
                    }

                    composable(
                        "composeReading?existingEntryId={existingEntryId}",
                        arguments = listOf(navArgument("existingEntryId") {
                            nullable = true
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val key = backStackEntry.arguments?.getString("existingEntryId")
                        val vm = viewModel<ComposerVm>(key, serviceFactory.create(key))

                        ReadingComposer(
                            vm::submitReading,
                            vm.currentHealthReading
                        ) {
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