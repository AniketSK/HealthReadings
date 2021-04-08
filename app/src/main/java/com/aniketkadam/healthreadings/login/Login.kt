package com.aniketkadam.healthreadings.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aniketkadam.healthreadings.realminit.InitializationState
import kotlinx.coroutines.launch


@Composable
fun Login(
    loginClicked: () -> Unit,
    username: MutableState<String>,
    password: MutableState<String>,
    networkResult: InitializationState,
    onSuccess: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {

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
                    value = username.value, onValueChange = { username.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.testTag("username")
                )
                OutlinedTextField(
                    value = password.value, onValueChange = { password.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.testTag("password")
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (networkResult == InitializationState.LOADING) {
                    CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
                } else {
                    Button(
                        loginClicked,
                        Modifier
                            .align(Alignment.End)
                            .testTag("loginButton")
                    ) {
                        Text("Login")
                    }
                }

                SideEffect {
                    when (networkResult) {
                        is InitializationState.ERROR -> scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(networkResult.message)
                        }
                        InitializationState.SUCCESS -> onSuccess()
                        InitializationState.LOADING,
                        InitializationState.WAITING -> {
                            // These conditions don't have side effects.
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun LoginPreview() {
    Login(
        { Log.d("tag", "Clicked") },
        mutableStateOf("Name"),
        mutableStateOf("pass"),
        InitializationState.WAITING
    ) {}
}