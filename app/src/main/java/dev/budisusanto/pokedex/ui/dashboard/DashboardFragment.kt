package dev.budisusanto.pokedex.ui.dashboard

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import dev.budisusanto.pokedex.App
import dev.budisusanto.pokedex.R
import dev.budisusanto.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.item_pokemon.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))
        dashboardViewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->
                textViewName.text = pokemon.name
                textViewDescription.text = pokemon.xdescription

                val color =
                    PokemonColorUtil(view.context).getPokemonColor(pokemon.typeofpokemon)
                app_bar.background.colorFilter =
                    PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                toolbar_layout.contentScrim?.colorFilter =
                    PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                activity?.window?.statusBarColor =
                    PokemonColorUtil(view.context).getPokemonColor(pokemon.typeofpokemon)

                app_bar.setBackgroundColor(PokemonColorUtil(view.context).getPokemonColor(pokemon.typeofpokemon))
                pokemon.typeofpokemon?.getOrNull(0).let { thirdType ->
                    Glide.with(textViewType3.context)
                        .load(App().checkInt(thirdType ?: ""))
                        .placeholder(android.R.color.transparent)
                        .into(textViewType3)
                    textViewType3.isVisible = thirdType != null
                }

                pokemon.typeofpokemon?.getOrNull(1).let { secondType ->
                    Glide.with(textViewType2.context)
                        .load(App().checkInt(secondType ?: ""))
                        .placeholder(android.R.color.transparent)
                        .into(textViewType2)
                    textViewType2.isVisible = secondType != null
                }

                pokemon.typeofpokemon?.getOrNull(2).let { firstType ->
                    Glide.with(textViewType1.context)
                        .load(App().checkInt(firstType ?: ""))
                        .placeholder(android.R.color.transparent)
                        .into(textViewType1)
                    textViewType1.isVisible =  firstType!= null
                }

                Glide.with(view.context)
                    .load(pokemon.imageurl)
                    .placeholder(android.R.color.transparent)
                    .into(imageView)

                val pager = viewPager
                val tabs = tabs
                pager.adapter =
                    ViewPagerAdapter(requireFragmentManager(), requireContext(), pokemon.id!!)
                tabs.setupWithViewPager(pager)
            }
        })
        app_bar.addOnOffsetChangedListener(appBarOffsetChangedListener)
        tv_exit.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }
    private val appBarOffsetChangedListener =
        AppBarLayout.OnOffsetChangedListener { _, offset ->
            if ((app_bar.totalScrollRange+offset == 0)) {
                imageView.visibility = View.GONE
                layHead.visibility = View.GONE
            } else {
                imageView.visibility = View.VISIBLE
                layHead.visibility = View.VISIBLE
            }
        }
}
