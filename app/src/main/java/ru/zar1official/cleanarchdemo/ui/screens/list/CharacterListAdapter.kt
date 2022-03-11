package ru.zar1official.cleanarchdemo.ui.screens.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import ru.zar1official.cleanarchdemo.R
import ru.zar1official.cleanarchdemo.databinding.CharacterListItemBinding
import ru.zar1official.cleanarchdemo.domain.models.Character

class CharacterListAdapter(
    private val onClick: (character: Character) -> Unit,
) :
    RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private val characterList = ArrayList<Character>()

    inner class CharacterViewHolder(characterView: View, clickAt: (Character) -> Unit) :
        RecyclerView.ViewHolder(characterView) {

        init {
            characterView.setOnClickListener {
                clickAt(characterList[adapterPosition])
            }
        }

        private val binding = CharacterListItemBinding.bind(characterView)

        fun bindNote(character: Character) = with(binding) {
            character.run {
                characterName.text = name
                characterImage.load(image){
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        return CharacterViewHolder(view) {
            onClick(it)
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindNote(characterList[position])
    }

    override fun getItemCount(): Int = characterList.size

    fun updateData(newList: List<Character>) {
        val diffCallback = CharacterDiffUtil(characterList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        characterList.clear()
        characterList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}