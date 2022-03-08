package ru.zar1official.cleanarchdemo.data.network.service

import retrofit2.http.GET
import ru.zar1official.cleanarchdemo.data.network.models.CharacterListEntity

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(): CharacterListEntity
}