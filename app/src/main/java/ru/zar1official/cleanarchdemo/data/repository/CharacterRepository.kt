package ru.zar1official.cleanarchdemo.data.repository

import ru.zar1official.cleanarchdemo.data.mappers.CharacterMapper
import ru.zar1official.cleanarchdemo.data.network.service.ApiService
import ru.zar1official.cleanarchdemo.domain.repository.Repository

class CharacterRepository(
    private val characterService: ApiService,
    private val characterMapper: CharacterMapper
) : Repository {
    override suspend fun getAllEntities() =
        characterMapper.mapFromEntityList(characterService.getAllCharacters())
}