package ru.zar1official.cleanarchdemo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.zar1official.cleanarchdemo.data.notificator.Notificator
import ru.zar1official.cleanarchdemo.data.notificator.NotificatorFirstImpl
import ru.zar1official.cleanarchdemo.data.notificator.NotificatorSecondImpl
import ru.zar1official.cleanarchdemo.ui.screens.list.CharactersListFragment
import ru.zar1official.cleanarchdemo.ui.screens.list.CharactersListViewModel

val appModule = module {
    viewModel<CharactersListViewModel> {
        CharactersListViewModel(get())
    }

    scope<CharactersListFragment> {
        scoped<Notificator>(qualifier = named("first_notificator")) { NotificatorFirstImpl(context = get()) }
        scoped<Notificator>(qualifier = named("second_notificator")) { NotificatorSecondImpl(context = get()) }
    }
}