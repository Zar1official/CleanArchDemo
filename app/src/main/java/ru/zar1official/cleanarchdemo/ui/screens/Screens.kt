package ru.zar1official.cleanarchdemo.ui.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.ui.screens.description.CharacterDescriptionFragment
import ru.zar1official.cleanarchdemo.ui.screens.list.CharactersListFragment

sealed class Screens {
    object ListOfCharactersScreen : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return CharactersListFragment.newInstance()
        }
    }

    data class CharacterDescriptionScreen(val character: Character) : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            return CharacterDescriptionFragment.newInstance(character)
        }

    }
}