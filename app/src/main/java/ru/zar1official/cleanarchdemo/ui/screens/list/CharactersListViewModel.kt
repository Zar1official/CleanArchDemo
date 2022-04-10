package ru.zar1official.cleanarchdemo.ui.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.domain.usecases.GetAllCharactersUseCase
import ru.zar1official.cleanarchdemo.util.SingleLiveEvent

class CharactersListViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase) :
    ViewModel() {
    val characters: Flow<CharactersState> = flow {
            emit(CharactersState.Loading)
            val result = getAllCharactersUseCase.invoke()
            emit(result)
    }.flowOn(Dispatchers.IO)

    private val _description = SingleLiveEvent<Character>()
    val description: LiveData<Character> = _description

    fun onOpenDescription(character: Character) {
        _description.value = character
    }

}
