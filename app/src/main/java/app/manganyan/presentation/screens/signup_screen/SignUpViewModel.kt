package app.manganyan.presentation.screens.signup_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()


    fun registerUser(email: String, password: String, pseudo: String) = viewModelScope.launch {

        repository.registerUser(email, password, pseudo).collect{
                result -> when(result){
            is Resource.Success -> {
                _signUpState.send(SignUpState(isSuccess = "SignIn Sucess"))

            }
            is Resource.Loading -> {
                _signUpState.send(SignUpState(isLoading = true))

            }
            is Resource.Error -> {
                _signUpState.send(SignUpState(isError = result.message))

            }
        }
        }
    }


}