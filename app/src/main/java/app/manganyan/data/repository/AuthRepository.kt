package app.manganyan.data.repository

import app.manganyan.common.Resource
import app.manganyan.domain.model.UserApp
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String, pseudo: String): Flow<Resource<AuthResult>>
    fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>
    fun getCurrentUser(id: String): Flow<Resource<UserApp>>
    fun getCurrentUserId(): String


}