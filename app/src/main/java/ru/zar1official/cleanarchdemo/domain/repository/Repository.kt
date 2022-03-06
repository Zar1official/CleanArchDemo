package ru.zar1official.cleanarchdemo.domain.repository

import ru.zar1official.cleanarchdemo.domain.models.Character

interface Repository {
    suspend fun getAllEntities(): List<Character>
}