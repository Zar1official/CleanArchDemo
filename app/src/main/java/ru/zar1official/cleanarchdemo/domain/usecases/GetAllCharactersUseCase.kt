package ru.zar1official.cleanarchdemo.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.zar1official.cleanarchdemo.di.Qualifiers
import ru.zar1official.cleanarchdemo.domain.repository.Repository
import ru.zar1official.cleanarchdemo.ui.screens.list.CharactersState
import javax.inject.Inject
import javax.inject.Singleton
class GetAllCharactersUseCase @Inject constructor(
    private val repository: Repository,
    @Qualifiers.IODispatcher private val IODispatcher: CoroutineDispatcher,
    @Qualifiers.DefaultDispatcher private val DefaultDispatcher: CoroutineDispatcher
) {
    suspend fun invoke(): CharactersState {
        val data =
            kotlin.runCatching { withContext(IODispatcher) { repository.getAllEntities() } }
                .getOrNull()
        return if (data != null)
            withContext(DefaultDispatcher) { CharactersState.Success(data.sortedBy { it.name }) }
        else
            CharactersState.Error
    }
}