package com.example.pokemon.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.databinding.FragmentListBinding
import com.example.pokemon.viewmodels.ListViewModel


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, ListViewModel.Factory(activity.application)).get(ListViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemonList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PokemonRVAdapter()
        }
        viewModel.pokemons.observe(viewLifecycleOwner) { pokemons ->
            if (pokemons.isNullOrEmpty()) {
                changeRVVisibility(false)
            } else {
                changeRVVisibility(true)
                (binding.pokemonList.adapter as? PokemonRVAdapter)?.pokemons = pokemons
            }
        }
    }

    private fun changeRVVisibility(visible: Boolean) {
        binding.pokemonList.visibility = if (visible) View.VISIBLE else View.GONE
        binding.emptyListMessage.visibility = if (!visible) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}