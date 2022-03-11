package ru.zar1official.cleanarchdemo.ui.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.domain.usecases.GetAllCharactersUseCase
import ru.zar1official.cleanarchdemo.util.SingleLiveEvent

class CharactersListViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase) :
    ViewModel() {
    val characters: LiveData<CharactersState> = liveData(Dispatchers.IO) {
        emit(CharactersState.Loading)
        val result = getAllCharactersUseCase.invoke()
        emit(result)
    }

    private val _description = SingleLiveEvent<Character>()
    val description: LiveData<Character> = _description

    fun onOpenDescription(character: Character) {
        _description.value = character
    }

}
