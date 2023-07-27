package app.manganyan.presentation.screens.login_screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.manganyan.R
import app.manganyan.presentation.navigation.Screens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import app.manganyan.presentation.ui.theme.BlueYellowGradient

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavHostController

) {

    val googleSignInState = viewModel.googleState.value

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }


    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)


    val cardHeight =
        with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp * 2 / 5 }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 30.dp)
        ) {
            Text(
                text = "Manganyan",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
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
                    ), shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
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
                    visualTransformation = PasswordVisualTransformation(),
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
                            viewModel.loginUser(email, password)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White, contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = "Continue", color = Color.Black, modifier = Modifier.padding(7.dp))
                }
                TextButton(
                    onClick = {
                        navController.navigate(Screens.SignUpScreen.route)
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 14.dp)
                ) {
                    Text(text = "No account? Register", color = Color.White)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (state.value?.isLoading == true) {
                        CircularProgressIndicator()
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    LaunchedEffect(key1 = state.value?.isSuccess) {
                        scope.launch {
                            if (state.value?.isSuccess?.isNotEmpty() == true) {
                                val success = state.value?.isSuccess
                                Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                                navController.navigate(Screens.MainScreen.route)
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

                    LaunchedEffect(key1 = googleSignInState.success) {
                        scope.launch {
                            if (googleSignInState.success != null) {
                                Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (googleSignInState.loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

}