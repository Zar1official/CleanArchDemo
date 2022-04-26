package ru.zar1official.cleanarchdemo.ui.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.zar1official.cleanarchdemo.R
import ru.zar1official.cleanarchdemo.data.notificator.Notificator
import ru.zar1official.cleanarchdemo.databinding.FragmentCharactersListBinding
import ru.zar1official.cleanarchdemo.di.Qualifiers
import ru.zar1official.cleanarchdemo.domain.models.Character
import ru.zar1official.cleanarchdemo.ui.screens.Screens
import javax.inject.Inject

@AndroidEntryPoint
class CharactersListFragment : Fragment() {
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharactersListViewModel by viewModels()

    private val characterListAdapter by lazy {
        CharacterListAdapter {
            viewModel.onOpenDescription(it)
        }
    }

    @Inject
    @Qualifiers.FirstNotificator
    lateinit var firstNotificator: Notificator

    @Inject
    @Qualifiers.SecondNotificator
    lateinit var secondNotificator: Notificator

    @Inject
    lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(layoutInflater, container, false).apply {
            characterList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = characterListAdapter
            }
            lifecycleScope.launchWhenStarted {
                viewModel.characters.collect { state ->
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
                        is CharactersState.Success -> {
                            progressBar.visibility = View.GONE
                            characterListAdapter.updateData(state.data)
                        }
                    }
                }

                viewModel.eventFlow.collectLatest {
                    when (it) {
                        is CharacterListEvent.OpenDescription -> openDescription(it.character)
                    }
                }
            }

            firstNotificator.notifyScreen()
            secondNotificator.notifyScreen()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openDescription(character: Character) {
        router.replaceScreen(Screens.CharacterDescriptionScreen(character))
    }

    companion object {
        @JvmStatic
        fun newInstance(): CharactersListFragment = CharactersListFragment()
    }
}