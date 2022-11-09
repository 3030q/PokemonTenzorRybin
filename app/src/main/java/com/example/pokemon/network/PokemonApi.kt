package com.example.pokemon.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("/api/v2/pokemon/{pokemon}")
    suspend fun getPokemon(@Path("pokemon") pokemon: String): Response<PokemonResponse>
}