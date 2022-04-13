package ru.zar1official.cleanarchdemo.di

import javax.inject.Qualifier

object Qualifiers {
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class IODispatcher()

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class DefaultDispatcher()

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class FirstNotificator()

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class SecondNotificator()
}