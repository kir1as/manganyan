package app.manganyan.presentation.screens.signup_screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.tooling.preview.Preview
import app.manganyan.R
import app.manganyan.presentation.ui.theme.FirstActivityTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state = viewModel.signUpState.collectAsState(initial = null)
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter your credential's to register",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.Gray,
            //fontFamily = RegularFont
        )
        TextField(
            value= email,
            onValueChange = {
                email = it
        },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Cyan, cursorColor = Color.Black),
            shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                Text(text = "Email")
            }
        //
        )
        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value= password,
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Cyan, cursorColor = Color.Black),
            shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                Text(text = "Password")
            }
            //
        )


        Button(onClick = {
                scope.launch {
                    viewModel.registerUser(email, password)
                }
                         
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 30.dp, end = 30.dp),
            colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black, contentColor = Color.White
            ), shape = RoundedCornerShape(15.dp) ) {
            Text(text = "Sign Up", color = Color.White, modifier = Modifier.padding(7.dp))

        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isLoading == true){
                CircularProgressIndicator()
            }
            
        }
        Text(text = "Already have an Account? SignIn", fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = "Or connect with", fontWeight = FontWeight.Medium, color = Color.Gray)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Google Icon", modifier = Modifier.size(50.dp), tint = Color.Unspecified )
                
            }
            
        }
        Spacer(modifier = Modifier.width(20.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Facebook Icon", modifier = Modifier.size(50.dp), tint = Color.Unspecified )

        }


        LaunchedEffect(key1 = state.value?.isSuccess){
            scope.launch {
                if(state.value?.isSuccess?.isNotEmpty() == true){
                    val success = state.value?.isSuccess
                    Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()

                }
            }
        }


        LaunchedEffect(key1 = state.value?.isError){
            scope.launch {
                if(state.value?.isError?.isNotEmpty() == true){
                    val error = state.value?.isError
                    Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()

                }
            }
        }
    }



}



@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreenPreview()
}


