package ru.zar1official.cleanarchdemo.ui.screens.list

import androidx.recyclerview.widget.DiffUtil
import ru.zar1official.cleanarchdemo.domain.models.Character

class CharacterDiffUtil(
    private val oldList: List<Character>,
    private val newList: List<Character>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.name == new.name && old.image == new.image && old.status == new.status
    }
}