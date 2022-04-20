package ru.zar1official.cleanarchdemo.domain.usecases

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.zar1official.cleanarchdemo.domain.repository.Repository
import ru.zar1official.cleanarchdemo.ui.screens.list.CharactersState

class GetAllCharactersUseCase(private val repository: Repository) {
    @SuppressLint("CheckResult")
    fun invoke(): Observable<CharactersState> {
        return Observable.create { emitter ->
            repository
                .getAllEntities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { emitter.onNext(CharactersState.Loading) }
                .subscribe({
                    emitter.onNext(CharactersState.Success(it))
                }, {
                    emitter.onNext(CharactersState.Error)
                })
        }
    }
}