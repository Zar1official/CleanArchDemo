package ru.zar1official.cleanarchdemo.ui.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import ru.zar1official.cleanarchdemo.R
import ru.zar1official.cleanarchdemo.data.notificator.Notificator
import ru.zar1official.cleanarchdemo.databinding.FragmentCharactersListBinding
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.ui.screens.description.CharacterDescriptionFragment

class CharactersListFragment : Fragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersListViewModel by viewModel()
    private val characterListAdapter by lazy {
        CharacterListAdapter {
            viewModel.onOpenDescription(it)
        }
    }
    private val firstNotificator: Notificator by inject(named("first_notificator"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(layoutInflater, container, false).apply {
            characterList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = characterListAdapter
            }
            viewModel.characters.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is CharactersState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is CharactersState.Error -> {
                        Toast.makeText(
                            context,
                            getString(R.string.error_message),
                            Toast.LENGTH_LONG
                        ).show()
                        progressBar.visibility = View.GONE
                    }

                    is CharactersState.Success-> {
                        progressBar.visibility = View.GONE
                        characterListAdapter.updateData(state.data)
                    }
                }
            }

            viewModel.description.observe(viewLifecycleOwner) { character ->
                openDescription(character)
            }

            firstNotificator.notifyScreen()

            viewModel.onLoadCharacters()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openDescription(character: Character) {
        parentFragmentManager.commit {
            replace(
                R.id.fragment_container,
                CharacterDescriptionFragment.newInstance(character)
            ).addToBackStack(
                CHARACTER_LIST_FRAGMENT_TAG
            )
        }
    }

    companion object {
        const val CHARACTER_LIST_FRAGMENT_TAG = "character_list_fragment"

        @JvmStatic
        fun newInstance(): CharactersListFragment = CharactersListFragment()
    }
}