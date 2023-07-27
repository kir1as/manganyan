package app.manganyan.presentation.screens.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.manganyan.common.Resource
import app.manganyan.data.repository.AuthRepository
import app.manganyan.presentation.screens.home.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _stateProfile = MutableStateFlow(ProfileState())
    val profileState = _stateProfile.asStateFlow()

    init {
        viewModelScope.launch {
            val idUser = getIdUser()
            getProfile(idUser)
        }
    }

    private suspend fun getProfile(id: String) = viewModelScope.launch {
        repository.getCurrentUser(id).collect { result ->
            when (result) {
                is Resource.Success -> _stateProfile.update { it ->
                    it.copy(
                        isLoading = false,
                        user = result.data ?: null,
                        error = ""
                    )
                }

                is Resource.Loading -> {
                    _stateProfile.value = ProfileState(isLoading = true)
                }

                is Resource.Error -> {
                    _stateProfile.value = ProfileState(error = result.message ?: "An Unexpected Error")
                }
            }
        }
    }

    private fun getIdUser(): String {
        return repository.getCurrentUserId()
    }


}