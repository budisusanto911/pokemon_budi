package dev.budisusanto.pokedex.ui.dashboard.evolution

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.budisusanto.pokedex.R
import dev.budisusanto.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.item_evolution.view.*

class EvolutionAdapter(
    private val context: Context
) : RecyclerView.Adapter<EvolutionAdapter.ViewHolder>() {

    private val list = arrayListOf<Pokemon>()

    fun setList(list: List<Pokemon>) {
        this.list.clear()
        this.list.addAll(list)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon, item1 : Pokemon) {
            itemView.visibility = View.VISIBLE
            itemView.textViewName.text = item.name
            itemView.status.text = item1.reason
            Glide.with(itemView.context)
                .load(item.imageurl)
                .placeholder(android.R.color.transparent)
                .into(itemView.imageView)

            itemView.textViewName2.text = item1.name

            Glide.with(itemView.context)
                .load(item1.imageurl)
                .placeholder(android.R.color.transparent)
                .into(itemView.imageView2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_evolution, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.size > position+1) {
            val item = list[position]
            val item1 = list[position + 1]
            holder.bindView(item, item1)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
