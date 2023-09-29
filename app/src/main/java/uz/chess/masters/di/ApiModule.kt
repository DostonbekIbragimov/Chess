package uz.chess.masters.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import uz.chess.masters.data.api.auth.AuthService
import uz.chess.masters.data.api.auth.AuthServiceImpl
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideAuthApi(client: HttpClient): AuthService {
        return AuthServiceImpl(client)
    }

}