package ru.zar1official.cleanarchdemo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.zar1official.cleanarchdemo.ui.screens.list.CharactersListViewModel

val appModule = module {
    viewModel<CharactersListViewModel> {
        CharactersListViewModel(get())
    }
}