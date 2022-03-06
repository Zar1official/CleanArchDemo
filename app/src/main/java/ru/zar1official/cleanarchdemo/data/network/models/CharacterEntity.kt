package ru.zar1official.cleanarchdemo.data.network.models

import com.google.gson.annotations.SerializedName

data class CharacterEntity(
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String
)