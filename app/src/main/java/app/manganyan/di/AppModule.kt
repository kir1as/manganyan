package app.manganyan.di

import app.manganyan.domain.repository.AuthRepository
import app.manganyan.data.repository.AuthRepositoryImpl
import app.manganyan.domain.repository.CommentRepository
import app.manganyan.data.repository.CommentRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideCommentRepositoryImpl(): CommentRepository {
        return CommentRepositoryImpl()
    }
}