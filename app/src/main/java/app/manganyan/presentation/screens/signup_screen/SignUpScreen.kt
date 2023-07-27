package app.manganyan.presentation.screens.signup_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.TextButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity


import androidx.navigation.NavHostController
import app.manganyan.presentation.navigation.Screens
import app.manganyan.presentation.ui.theme.BlueYellowGradient
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController

) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state = viewModel.signUpState.collectAsState(initial = null)
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val cardHeight =
        with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp * 2 / 5 }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 30.dp)
        ) {
            Text(
                text = "Manganyan",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 16.dp),
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .align(Alignment.BottomCenter)
                .height(cardHeight),
            shape = RoundedCornerShape(28.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlueYellowGradient),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                        Text(text = "Email")
                    }
                )
                Divider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                )
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                        Text(text = "Password")
                    }
                )
                Divider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                )
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.registerUser(email, password)
                        }

                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White, contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = "Register", color = Color.Black, modifier = Modifier.padding(7.dp))
                }
                TextButton(
                    onClick = {
                        navController.navigate(Screens.SignInScreen.route)
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 14.dp)
                ) {
                    Text(text = "Have account? Sign In", color = Color.White)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (state.value?.isLoading == true) {
                        CircularProgressIndicator()
                    }
                }

                LaunchedEffect(key1 = state.value?.isSuccess) {
                    scope.launch {
                        if (state.value?.isSuccess?.isNotEmpty() == true) {
                            val success = state.value?.isSuccess
                            Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                            navController.navigate(Screens.HomeScreen.route)
                        }
                    }
                }


                LaunchedEffect(key1 = state.value?.isError) {
                    scope.launch {
                        if (state.value?.isError?.isNotEmpty() == true) {
                            val error = state.value?.isError
                            Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}






