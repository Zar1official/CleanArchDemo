package ru.zar1official.cleanarchdemo.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val image: String = ""
): Parcelable
