package dev.budisusanto.pokedex.repository

import dev.budisusanto.pokedex.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon.json")
    fun get(): Call<List<Pokemon>>
}
