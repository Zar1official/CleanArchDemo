package ru.zar1official.cleanarchdemo.domain.repository

import io.reactivex.Single
import io.reactivex.disposables.Disposable
import ru.zar1official.cleanarchdemo.data.network.models.CharacterListEntity
import ru.zar1official.cleanarchdemo.domain.models.Character

interface Repository {
    fun getAllEntities(): Single<List<Character>>
}