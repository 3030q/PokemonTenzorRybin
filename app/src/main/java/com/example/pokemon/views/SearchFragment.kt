package com.example.pokemon.views

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokemon.databinding.FragmentSearchBinding
import com.example.pokemon.viewmodels.SearchViewModel
import com.google.android.material.snackbar.Snackbar


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, SearchViewModel.Factory(activity.application)).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputConfirm.setOnClickListener {
            hideKeyboard()
            viewModel.findPokemon(binding.inputField.text.toString())
        }

        viewModel.validationError.observe(viewLifecycleOwner) { error ->
            binding.inputField.error = error
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.inputConfirm.isEnabled = !isLoading
            binding.loadingIcon.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorText.observe(viewLifecycleOwner) { error ->
            Snackbar.make(binding.root, "Ошибка: $error", Snackbar.LENGTH_LONG).show()
        }

        viewModel.result.observe(viewLifecycleOwner) { result ->
            if (result) {
                Toast.makeText(activity, "Покемон найден!", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}