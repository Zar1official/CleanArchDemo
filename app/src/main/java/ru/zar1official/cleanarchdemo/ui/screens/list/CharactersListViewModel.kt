package ru.zar1official.cleanarchdemo.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.domain.usecases.GetAllCharactersUseCase
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(private val getAllCharactersUseCase: GetAllCharactersUseCase) :
    ViewModel() {
    val characters: Flow<CharactersState> = flow {
        emit(CharactersState.Loading)
        val result = getAllCharactersUseCase.invoke()
        emit(result)
    }

    private val _eventFlow = MutableSharedFlow<CharacterListEvent>()
    val eventFlow: SharedFlow<CharacterListEvent> = _eventFlow

    fun onOpenDescription(character: Character) {
        viewModelScope.launch {
            _eventFlow.emit(CharacterListEvent.OpenDescription(character))
        }
    }

}
