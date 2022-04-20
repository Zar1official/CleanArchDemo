package ru.zar1official.cleanarchdemo.data.repository

import io.reactivex.Single
import ru.zar1official.cleanarchdemo.data.mappers.CharacterMapper
import ru.zar1official.cleanarchdemo.data.network.service.ApiService
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.domain.repository.Repository

class CharacterRepository(
    private val characterService: ApiService,
    private val characterMapper: CharacterMapper
) : Repository {
    override fun getAllEntities(): Single<List<Character>> =
        characterService
            .getAllCharacters().map { characterMapper.mapFromEntityList(it) }
}