package ru.zar1official.cleanarchdemo.data.notificator

import android.content.Context
import android.widget.Toast

class NotificatorSecondImpl(private val context: Context) : Notificator {
    override fun notifyScreen() {
        Toast.makeText(context, "second_message", Toast.LENGTH_LONG).show()
    }
}