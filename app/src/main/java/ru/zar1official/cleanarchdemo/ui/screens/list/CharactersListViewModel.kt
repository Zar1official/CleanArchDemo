package ru.zar1official.cleanarchdemo.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.domain.usecases.GetAllCharactersUseCase
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(private val getAllCharactersUseCase: GetAllCharactersUseCase) :
    ViewModel() {
    val characters: Flow<CharactersState> = flow {
        delay(2000L)
        emit(CharactersState.Loading)
        val result = getAllCharactersUseCase.invoke()
        emit(result)
    }.flowOn(Dispatchers.IO)

    private val _eventFlow = MutableSharedFlow<CharacterListEvent>()
    val eventFlow: SharedFlow<CharacterListEvent> = _eventFlow

    fun onOpenDescription(character: Character) {
        viewModelScope.launch {
            _eventFlow.emit(CharacterListEvent.OpenDescription(character))
        }
    }

}
