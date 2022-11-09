package com.example.pokemon.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemon.databinding.FragmentListRvBinding
import com.example.pokemon.ext.capitalizeFirstCharacter
import com.example.pokemon.model.Pokemon

class PokemonRVAdapter: RecyclerView.Adapter<PokemonRVViewHolder>() {

    var pokemons: List<Pokemon> = emptyList()
        set(value) {
            field = value
            // For an extra challenge, update this to use the paging library.

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonRVViewHolder {
        val withDataBinding: FragmentListRvBinding = FragmentListRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return PokemonRVViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: PokemonRVViewHolder, position: Int) {
        holder.setData(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }
}

class PokemonRVViewHolder(val binding: FragmentListRvBinding): RecyclerView.ViewHolder(binding.root) {

    fun setData(pokemon: Pokemon) {
        binding.name.text = pokemon.name.capitalizeFirstCharacter()
        binding.types.text = getType(pokemon.types)
        try {
            Glide.with(binding.root).load(pokemon.url).diskCacheStrategy(DiskCacheStrategy.ALL).into(binding.image)
        } catch (_: Exception) {

        }
    }

    private fun getType(types: List<String>?): String {
        if (types.isNullOrEmpty()) return ""
        var type = "TYPE: "
        types.forEach { type += "${it.capitalizeFirstCharacter()}, " }
        return type.dropLast(2)
    }
}