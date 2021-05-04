package dev.budisusanto.pokedex.ui.pokedex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.budisusanto.pokedex.App
import dev.budisusanto.pokedex.MainActivity
import dev.budisusanto.pokedex.R
import dev.budisusanto.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*
import java.util.*

class PokemonAdapter(
    private val list: List<Pokemon>,
    private val context: Context
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon) {
            itemView.textViewName.text = item.name
            itemView.textViewID.text = item.id

            item.typeofpokemon?.getOrNull(0).let { thirdType ->
                Glide.with(itemView.context)
                    .load(App().checkInt(thirdType ?: ""))
                    .placeholder(android.R.color.transparent)
                    .into(itemView.textViewType3)
                itemView.textViewType3.isVisible = thirdType != null
            }

            item.typeofpokemon?.getOrNull(1).let { secondType ->
                Glide.with(itemView.context)
                    .load(App().checkInt(secondType ?: ""))
                    .placeholder(android.R.color.transparent)
                    .into(itemView.textViewType2)
                itemView.textViewType2.isVisible = secondType != null
            }

            item.typeofpokemon?.getOrNull(2).let { firstType ->
                Glide.with(itemView.context)
                    .load(App().checkInt(firstType ?: ""))
                    .placeholder(android.R.color.transparent)
                    .into(itemView.textViewType1)
                itemView.textViewType1.isVisible =  firstType!= null
            }

            Glide.with(itemView.context)
                .load(item.imageurl)
                .placeholder(android.R.color.transparent)
                .into(itemView.imageView)

            itemView.setOnClickListener {
                val bundle = bundleOf("id" to item.id)
                it.findNavController()
                    .navigate(R.id.action_navigation_pokedex_to_navigation_dashboard, bundle)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
