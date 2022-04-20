package ru.zar1official.cleanarchdemo.di

import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.zar1official.cleanarchdemo.data.mappers.CharacterMapper
import ru.zar1official.cleanarchdemo.data.network.Constants.BASE_URL
import ru.zar1official.cleanarchdemo.data.network.service.ApiService
import ru.zar1official.cleanarchdemo.data.repository.CharacterRepository
import ru.zar1official.cleanarchdemo.domain.repository.Repository

private fun provideRetrofitClient(): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        ).build()

private fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

val dataModule = module {
    single<ApiService> {
        provideApiService(retrofit = get())
    }

    single<Retrofit> {
        provideRetrofitClient()
    }

    single<Repository> {
        return@single CharacterRepository(characterMapper = get(), characterService = get())
    }

    factory<CharacterMapper> {
        CharacterMapper()
    }
}