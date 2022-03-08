package ru.zar1official.cleanarchdemo.ui.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.zar1official.cleanarchdemo.databinding.FragmentCharactersListBinding

class CharactersListFragment : Fragment() {
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersListViewModel by viewModel()
    private val characterListAdapter by lazy {
        CharacterListAdapter {
            viewModel.onOpenDescription(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(layoutInflater, container, false).apply {
            viewModel.characters.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is CharactersState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is CharactersState.Error -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        progressBar.visibility = View.GONE
                    }
                    is CharactersState.Success -> {
                        progressBar.visibility = View.GONE
                        characterList.apply {
                            layoutManager = GridLayoutManager(context, 2)
                            adapter = characterListAdapter
                        }
                    }
                }
            }

            viewModel.description.observe(viewLifecycleOwner) { character ->

            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun openDescription() {

    }

    companion object {
        @JvmStatic
        fun newInstance(): CharactersListFragment = CharactersListFragment()
    }
}