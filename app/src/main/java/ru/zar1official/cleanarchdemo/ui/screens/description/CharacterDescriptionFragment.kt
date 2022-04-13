package ru.zar1official.cleanarchdemo.ui.screens.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import ru.zar1official.cleanarchdemo.R
import ru.zar1official.cleanarchdemo.data.notificator.Notificator
import ru.zar1official.cleanarchdemo.databinding.FragmentCharacterDescriptionBinding
import ru.zar1official.cleanarchdemo.di.Qualifiers
import ru.zar1official.cleanarchdemo.domain.models.Character
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDescriptionFragment : Fragment() {
    private val character: Character by lazy {
        arguments?.getParcelable(CHARACTER_KEY) ?: Character()
    }
    @Inject
    @Qualifiers.FirstNotificator
    lateinit var notificator: Notificator
    private var _binding: FragmentCharacterDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentCharacterDescriptionBinding.inflate(layoutInflater, container, false).apply {
                characterDescriptionName.text = character.name
                characterDescriptionStatus.text =
                    "${getString(R.string.status_text)}${character.status}"
                characterDescriptionImage.load(character.image) {
                    crossfade(true)
                    crossfade(300)
                    transformations(CircleCropTransformation())
                }
            }
        notificator.notifyScreen()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val CHARACTER_KEY = "character"

        @JvmStatic
        fun newInstance(character: Character) =
            CharacterDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CHARACTER_KEY, character)
                }
            }
    }
}