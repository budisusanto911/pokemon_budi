package dev.budisusanto.pokedex

import android.app.Application
import dev.budisusanto.pokedex.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidContext(this@App)
        modules(appComponent)
    }
    fun checkInt(nama : String = "") : Int{
        var int = 0
        when {
            nama.toLowerCase(Locale.getDefault()) == "normal" -> int = R.drawable.normal
            nama.toLowerCase(Locale.getDefault()) == "electric" -> int = R.drawable.electrik
            nama.toLowerCase(Locale.getDefault()) == "psychic" -> int = R.drawable.mystic
            nama.toLowerCase(Locale.getDefault()) == "flying" -> int = R.drawable.flying
            nama.toLowerCase(Locale.getDefault()) == "fire" -> int = R.drawable.fire
            nama.toLowerCase(Locale.getDefault()) == "water" -> int = R.drawable.water
            nama.toLowerCase(Locale.getDefault()) == "ice" -> int = R.drawable.es
            nama.toLowerCase(Locale.getDefault()) == "grass" -> int = R.drawable.grass
            nama.toLowerCase(Locale.getDefault()) == "poison" -> int = R.drawable.poison
            nama.toLowerCase(Locale.getDefault()) == "ghost" -> int = R.drawable.ghost
            nama.toLowerCase(Locale.getDefault()) == "dragon" -> int = R.drawable.dragon
            nama.toLowerCase(Locale.getDefault()) == "ground" -> int = R.drawable.ground
            nama.toLowerCase(Locale.getDefault()) == "steel" -> int = R.drawable.steel
            nama.toLowerCase(Locale.getDefault()) == "bug" -> int = R.drawable.bug
            nama.toLowerCase(Locale.getDefault()) == "fighting" -> int = R.drawable.figth
            nama.toLowerCase(Locale.getDefault()) == "dark" -> int = R.drawable.dark
            nama.toLowerCase(Locale.getDefault()) == "rock" -> int = R.drawable.rock
            nama.toLowerCase(Locale.getDefault()) == "fairy" -> int = R.drawable.fairy
        }
        return int
    }

    fun type () : String{
        return "normal, electric, psychic, flying, fire, water, ice, grass, poison, ghost, dragon, ground, steel, fighting, bug, dark, rock, fairy"
    }
}
