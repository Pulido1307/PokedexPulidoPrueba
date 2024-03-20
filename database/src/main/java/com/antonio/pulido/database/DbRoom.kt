package com.antonio.pulido.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antonio.pulido.database.dao.PokeDao
import com.antonio.pulido.domain.entities.PokeEntity

@Database(entities = [PokeEntity::class], version = 1, exportSchema = false)
abstract class DbRoom : RoomDatabase() {
    abstract fun pokeDao(): PokeDao
    companion object {
        @JvmStatic
        fun newInstance(context: Context): DbRoom {
            return Room.databaseBuilder(context, DbRoom::class.java, "DbPokedex")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}