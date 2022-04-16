package ru.zar1official.cleanarchdemo.domain.usecases

import io.reactivex.Single
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.domain.repository.Repository
import java.util.concurrent.TimeUnit

class GetAllCharactersUseCase(private val repository: Repository) {
    fun invoke(): Single<List<Character>> {
//        val data = kotlin.runCatching { repository.getAllEntities() }.getOrNull()
//        return if (data != null)
//            CharactersState.Success(data)
//        else
//            CharactersState.Error
        return Single.just(listOf(Character(id = 0, name = "Test", status = "alive", image = ""))).delay(3000, TimeUnit.MILLISECONDS)
    }
}