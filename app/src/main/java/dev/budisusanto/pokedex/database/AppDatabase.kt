package dev.budisusanto.pokedex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.budisusanto.pokedex.database.dao.PokemonDAO
import dev.budisusanto.pokedex.model.Pokemon

@Database(entities = [Pokemon::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO
}
