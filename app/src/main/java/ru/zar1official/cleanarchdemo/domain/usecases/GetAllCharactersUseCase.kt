package ru.zar1official.cleanarchdemo.domain.usecases

import ru.zar1official.cleanarchdemo.domain.repository.Repository
import ru.zar1official.cleanarchdemo.ui.screens.list.CharactersState

class GetAllCharactersUseCase(private val repository: Repository) {
    suspend fun invoke(): CharactersState {
        val data = kotlin.runCatching { repository.getAllEntities() }.getOrNull()
        return if (data != null)
            CharactersState.Success(data)
        else
            CharactersState.Error
    }
}