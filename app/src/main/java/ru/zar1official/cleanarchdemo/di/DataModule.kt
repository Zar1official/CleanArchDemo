package ru.zar1official.cleanarchdemo.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.zar1official.cleanarchdemo.data.network.Constants.BASE_URL
import ru.zar1official.cleanarchdemo.data.network.service.ApiService
import ru.zar1official.cleanarchdemo.data.repository.CharacterRepository
import ru.zar1official.cleanarchdemo.domain.repository.Repository
import ru.zar1official.cleanarchdemo.domain.usecases.GetAllCharactersUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            ).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @InstallIn(SingletonComponent::class)
    @Module
    interface Binds {
        @dagger.Binds
        fun bindRepository(characterRepository: CharacterRepository): Repository
    }
}