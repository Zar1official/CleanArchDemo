package ru.zar1official.cleanarchdemo.data.mappers

import ru.zar1official.cleanarchdemo.data.network.models.CharacterEntity
import ru.zar1official.cleanarchdemo.data.network.models.CharacterListEntity
import ru.zar1official.cleanarchdemo.domain.models.Character
import javax.inject.Inject

class CharacterMapper @Inject constructor() {
    fun mapFromEntityList(entityList: CharacterListEntity) =
        entityList.results.map { mapFromEntity(it) }

    private fun mapFromEntity(entity: CharacterEntity): Character =
        Character(id = entity.id, name = entity.name, status = entity.status, image = entity.image)
}