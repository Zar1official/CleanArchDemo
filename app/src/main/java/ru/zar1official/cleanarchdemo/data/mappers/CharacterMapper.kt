package ru.zar1official.cleanarchdemo.data.mappers

import ru.zar1official.cleanarchdemo.data.network.models.CharacterEntity
import ru.zar1official.cleanarchdemo.domain.models.Character

class CharacterMapper {

    fun mapFromEntity(entity: CharacterEntity) = Character(
        title = entity.title,
        content = entity.content
    )

    fun mapFromEntityList(listOfEntity: List<CharacterEntity>) =
        listOfEntity.map { mapFromEntity(it) }
}