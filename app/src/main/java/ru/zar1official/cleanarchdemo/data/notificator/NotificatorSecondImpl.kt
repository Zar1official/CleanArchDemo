package ru.zar1official.cleanarchdemo.data.notificator

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class NotificatorSecondImpl @Inject constructor(private val context: Context) : Notificator {
    override fun notifyScreen() {
        Toast.makeText(context, "second_message", Toast.LENGTH_LONG).show()
    }
}