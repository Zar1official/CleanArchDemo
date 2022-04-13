package ru.zar1official.cleanarchdemo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import ru.zar1official.cleanarchdemo.data.notificator.Notificator
import ru.zar1official.cleanarchdemo.data.notificator.NotificatorFirstImpl
import ru.zar1official.cleanarchdemo.data.notificator.NotificatorSecondImpl

@Module
@InstallIn(FragmentComponent::class)
abstract class NotificatorModule {
    @Binds
    @Qualifiers.FirstNotificator
    abstract fun firstNotificator(firstImpl: NotificatorFirstImpl): Notificator

    @Binds
    @Qualifiers.SecondNotificator
    abstract fun secondNotificator(secondImpl: NotificatorSecondImpl): Notificator
}