package ru.zar1official.cleanarchdemo.ui.screens.list

import android.text.style.CharacterStyle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.zar1official.cleanarchdemo.data.network.models.CharacterListEntity
import ru.zar1official.cleanarchdemo.data.network.service.ApiService
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.domain.repository.Repository
import ru.zar1official.cleanarchdemo.domain.usecases.GetAllCharactersUseCase
import ru.zar1official.cleanarchdemo.util.SingleLiveEvent

class CharactersListViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase) :
    ViewModel() {
    private val disposable = CompositeDisposable()

    private val _characters = MutableLiveData<CharactersState>()
    val characters: LiveData<CharactersState> = _characters

    fun onLoadCharacters() {
        disposable.add(
            getAllCharactersUseCase
                .invoke()
                .subscribe({
                   _characters.value= it
                }, {
                    _characters.value = CharactersState.Error
                })
        )
    }

    private val _description = SingleLiveEvent<Character>()
    val description: LiveData<Character> = _description

    fun onOpenDescription(character: Character) {
        _description.value = character
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
