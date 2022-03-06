package ru.zar1official.cleanarchdemo.ui.screens.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.zar1official.cleanarchdemo.R

class CharacterDescriptionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_description, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CharacterDescriptionFragment()
    }
}