package com.practica_s06.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practica_s06.model.Estado

@Database(entities = [Estado::class], version = 1, exportSchema = false)
abstract class PracticaDatabase : RoomDatabase() {

    abstract fun estadoDao(): EstadoDao

    companion object {
        @Volatile
        private var INSTANCE: PracticaDatabase? = null

        fun getDatabase(context: Context): PracticaDatabase {
            val local = INSTANCE
            if (local != null) {
                return local
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PracticaDatabase::class.java,
                    "practica"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}