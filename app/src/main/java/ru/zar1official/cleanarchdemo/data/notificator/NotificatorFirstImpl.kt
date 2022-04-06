package ru.zar1official.cleanarchdemo.data.notificator

import android.content.Context
import android.widget.Toast

class NotificatorFirstImpl(private val context: Context) : Notificator {
    override fun notifyScreen() {
        Toast.makeText(context, "first_message", Toast.LENGTH_LONG).show()
    }
}