package app.manganyan.data.repository

import app.manganyan.common.Resource
import app.manganyan.domain.model.UserApp
import app.manganyan.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

    private val firebaseAuth: FirebaseAuth
): AuthRepository {
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {


        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }
    }

    override fun registerUser(email: String, password: String, pseudo: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            try {
                val userMap = mapOf(
                    "email" to email,
                    "pseudo" to pseudo,
                )
                val userId = result.user?.uid
                if (userId != null) {
                    firebaseFirestore.collection("users")
                        .document(userId)
                        .set(userMap)
                        .await()
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message.toString()))
            }
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }
    }

    override fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }
    }

    override fun getCurrentUser(id: String): Flow<Resource<UserApp>> {
        return flow {
            emit(Resource.Loading())

            try {
                val usersCollection = firebaseFirestore.collection("users")
                val userDocument = usersCollection.document(id).get().await()

                if (userDocument.exists()) {
                    val email = userDocument.getString("email") ?: ""
                    val pseudo = userDocument.getString("pseudo") ?: ""
                    val user = UserApp(id, email, pseudo)
                    if (user != null) {
                        emit(Resource.Success(user))
                    } else {
                        emit(Resource.Error(message = "User is null"))
                    }
                } else {
                    emit(Resource.Error(message = "User not found"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: "An error occurred"))
            }
        }
    }

    override fun getCurrentUserId(): String {
        val currentUser = firebaseAuth.currentUser
        val currentUserId = currentUser?.uid
        return currentUserId ?: ""
    }
}