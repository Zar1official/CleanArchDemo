package ru.zar1official.cleanarchdemo.ui.screens.list

import ru.zar1official.cleanarchdemo.domain.models.Character

sealed class CharacterListEvent{
    data class OpenDescription(val character: Character): CharacterListEvent()
}
