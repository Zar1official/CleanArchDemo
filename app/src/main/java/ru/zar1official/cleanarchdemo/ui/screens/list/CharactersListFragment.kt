package ru.zar1official.cleanarchdemo.ui.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.zar1official.cleanarchdemo.databinding.FragmentCharactersListBinding

class CharactersListFragment : Fragment() {
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(layoutInflater, container, false).apply {
            viewModel.characters.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is CharactersState.Loading ->
                        progressBar.visibility = View.VISIBLE
                    is CharactersState.Error -> {
                        progressBar.visibility = View.GONE
                    }
                    is CharactersState.Success -> {
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(): CharactersListFragment = CharactersListFragment()
    }
}