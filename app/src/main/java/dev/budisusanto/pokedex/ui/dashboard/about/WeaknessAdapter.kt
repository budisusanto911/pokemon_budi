package dev.budisusanto.pokedex.ui.dashboard.about

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.budisusanto.pokedex.App
import dev.budisusanto.pokedex.R
import dev.budisusanto.pokedex.model.Weakness
import kotlinx.android.synthetic.main.item_evolution.view.*

class WeaknessAdapter(
    private val context: Context
) : RecyclerView.Adapter<WeaknessAdapter.ViewHolder>() {

    private val list = arrayListOf<Weakness>()

    fun setList(list: List<Weakness>) {
        this.list.clear()
        this.list.addAll(list)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: Weakness) {
            itemView.visibility = View.VISIBLE
            itemView.textViewName.text = item.jumlah.toString() + " x"
            Glide.with(itemView.context)
                .load(App().checkInt(item.name))
                .placeholder(android.R.color.transparent)
                .into(itemView.imageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_weakness, parent, false)
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
