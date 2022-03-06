package ru.zar1official.cleanarchdemo.data.network.service

import retrofit2.http.GET
import ru.zar1official.cleanarchdemo.data.network.models.CharacterEntity

interface ApiService {
    @GET("note.json?alt=media")
    suspend fun getAllCharacters(): CharacterEntity
}