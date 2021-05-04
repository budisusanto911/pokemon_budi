package dev.budisusanto.pokedex.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.budisusanto.pokedex.database.dao.PokemonDAO
import dev.budisusanto.pokedex.model.Pokemon

class DashboardViewModel(private val pokemonDAO: PokemonDAO) : ViewModel() {

    fun getPokemonById(id: String?): LiveData<Pokemon> {
        return pokemonDAO.getById(id)
    }

    fun getPokemonEvolutionsByIds(ids: List<String>): LiveData<List<Pokemon>> {
        return pokemonDAO.getEvolutionsByIds(ids)
    }
}
