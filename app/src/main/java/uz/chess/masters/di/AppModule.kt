package uz.chess.masters.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.websocket.WebSockets
import uz.chess.masters.data.repository.MainRepository
import uz.chess.masters.data.repository.MainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets) {
                pingInterval = 20_000
            }
        }
    }

    @Singleton
    @Provides
    fun provideMainRepository(httpClient: HttpClient): MainRepository {
        return MainRepositoryImpl(httpClient)
    }
}