package com.example.pokemon.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentInitialBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class InitialFragment : Fragment() {

    private var _binding: FragmentInitialBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInitialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSearch.setOnClickListener {
            findNavController().navigate(R.id.action_InitialFragment_to_SearchFragment)
        }

        binding.buttonList.setOnClickListener {
            findNavController().navigate(R.id.action_InitialFragment_to_ListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}