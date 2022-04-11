package ru.zar1official.cleanarchdemo.ui.screens.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.domain.usecases.GetAllCharactersUseCase

class CharactersListViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase) :
    ViewModel() {
    val characters: Flow<CharactersState> = flow {
        emit(CharactersState.Loading)
        val result = getAllCharactersUseCase.invoke()
        emit(result)
    }.flowOn(Dispatchers.IO)

    private val eventChannel = Channel<CharacterListEvent>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun onOpenDescription(character: Character) {
        eventChannel.trySend(CharacterListEvent.OpenDescription(character))
    }

}
