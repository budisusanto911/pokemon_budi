package dev.budisusanto.pokedex.ui.dashboard.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import dev.budisusanto.pokedex.R
import dev.budisusanto.pokedex.model.Weakness
import dev.budisusanto.pokedex.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.item_abiliti.*
import kotlinx.android.synthetic.main.item_breeding.*
import kotlinx.android.synthetic.main.item_evolution.view.*
import kotlinx.android.synthetic.main.item_status.*
import kotlinx.android.synthetic.main.spirit.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class AboutFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = AboutFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    private val dashboardViewModel: DashboardViewModel by viewModel()
    private val type = arrayOf("normal", "electric", "psychic", "flying", "fire", "water", "ice", "grass", "poison", "ghost", "dragon", "ground", "steel", "fighting", "bug", "dark", "rock", "fairy")
    private val listType = ArrayList<Weakness>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))
        Log.i("TAG", "onViewCreated: https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/"+id.replace("#", "").replace("0", "")+".png" )
        var abiliti = ""
        val recyclerView = rvWeakness
        val layoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = layoutManager
        val adapter = WeaknessAdapter(view.context)
        recyclerView.adapter = adapter
        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->
                textViewGender.text = pokemon.male_percentage + "\n" + pokemon.female_percentage
                textViewEggCycle.text = pokemon.cycles
                textViewEggGroups.text = pokemon.egg_groups

                pokemon.abilities?.forEach {
                    abiliti += " $it"
                }

                progress.progress = pokemon.male_percentage?.replace("%", "")?.toDouble()?.toInt() ?: 0
                textViewTypeDefenses.text = abiliti
                txtAbilitiesDesc.text = pokemon.ydescription

                textViewHP.text = pokemon.hp.toString()
                textViewAttack.text = pokemon.attack.toString()
                textViewDefense.text = pokemon.defense.toString()
                textViewSpAtk.text = pokemon.special_attack.toString()
                textViewSpDef.text = pokemon.special_defense.toString()
                textViewSpeed.text = pokemon.speed.toString()
                textViewTotal.text = pokemon.total.toString()

                progressBarHP.progress = pokemon.hp ?: 0
                progressBarAttack.progress = pokemon.attack ?: 0
                progressBarDefense.progress = pokemon.defense ?: 0
                progressBarSpAtk.progress = pokemon.special_attack ?: 0
                progressBarSpDef.progress = pokemon.special_defense ?: 0
                progressBarSpeed.progress = pokemon.speed ?: 0
                progressBarTotal.progress = pokemon.total ?: 0

                type.forEachIndexed { index, s ->
                    listType.add(Weakness(type[index], 1))
                }
                listType.forEach { weak ->
                    pokemon.weaknesses?.forEach {
                        if (weak.name == it.toLowerCase(Locale.getDefault())) weak.jumlah = 2
                    }
                }
                adapter.setList(listType)
                adapter.notifyDataSetChanged()
                /*dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
                    pokemonValue?.let { pokemon ->
                        val evolutions = pokemon.evolutions ?: emptyList()
                dashboardViewModel.getPokemonEvolutionsByIds(evolutions).observe(viewLifecycleOwner, Observer {*/
                    Glide.with(view.context)
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/"+id.replace("#", "").replace("0", "")+".png")
                        .placeholder(android.R.color.transparent)
                        .into(imageViewNormal)
                    Glide.with(view.context)
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/"+id.replace("#", "").replace("0", "")+".png")
                        .placeholder(android.R.color.transparent)
                        .into(imageViewShiny)
                //})}})
            }
        })
    }

}
