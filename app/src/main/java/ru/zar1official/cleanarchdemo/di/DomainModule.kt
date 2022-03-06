package ru.zar1official.cleanarchdemo.di

import org.koin.dsl.module
import ru.zar1official.cleanarchdemo.domain.usecases.GetAllCharactersUseCase

val domainModule = module {
    factory<GetAllCharactersUseCase> {
        GetAllCharactersUseCase(repository = get())
    }
}