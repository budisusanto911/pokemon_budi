package dev.budisusanto.pokedex.di

import dev.budisusanto.pokedex.ui.dashboard.DashboardViewModel
import dev.budisusanto.pokedex.ui.pokedex.PokedexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { PokedexViewModel(get(), get()) }
}
