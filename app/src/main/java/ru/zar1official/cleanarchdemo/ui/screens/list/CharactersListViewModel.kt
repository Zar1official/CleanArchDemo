package ru.zar1official.cleanarchdemo.ui.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.domain.usecases.GetAllCharactersUseCase
import ru.zar1official.cleanarchdemo.util.SingleLiveEvent

class CharactersListViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase) :
    ViewModel() {
    //    val characters: LiveData<CharactersState> = liveData(Dispatchers.IO) {
//        emit(CharactersState.Loading)
//        val result = getAllCharactersUseCase.invoke()
//        emit(result)
//    }
    private val _characters = MutableLiveData<CharactersState>()
    val characters: LiveData<CharactersState> = _characters
    fun onLoadCharacters() {
        val a = getAllCharactersUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _characters.value = CharactersState.Loading }
            .subscribe({
                _characters.value = CharactersState.Success(it)
            }, {
                _characters.value = CharactersState.Error
            })
    }

    private val _description = SingleLiveEvent<Character>()
    val description: LiveData<Character> = _description

    fun onOpenDescription(character: Character) {
        _description.value = character
    }

}
