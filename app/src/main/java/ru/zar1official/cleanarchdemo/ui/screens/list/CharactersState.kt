package ru.zar1official.cleanarchdemo.ui.screens.list

import ru.zar1official.cleanarchdemo.domain.models.Character

sealed class CharactersState {
    object Loading : CharactersState()
    data class Success(val data: List<Character>) : CharactersState()
    object Error : CharactersState()
}
