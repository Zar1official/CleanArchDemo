package ru.zar1official.cleanarchdemo.data.network.service

import io.reactivex.Single
import retrofit2.http.GET
import ru.zar1official.cleanarchdemo.data.network.models.CharacterListEntity

interface ApiService {
    @GET("character")
    fun getAllCharacters(): Single<CharacterListEntity>
}