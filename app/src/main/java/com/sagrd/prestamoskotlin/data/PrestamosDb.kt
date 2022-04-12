package com.sagrd.prestamoskotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sagrd.prestamoskotlin.model.Ocupacion

@Database(
    entities = [Ocupacion::class],
    version = 1
)
abstract class PrestamosDb: RoomDatabase() {
    abstract  val ocupacionDao: OcupacionDao

    companion object {
        private const val DATABASE_NAME = "PrestamosDb"
        @Volatile private var instance: PrestamosDb? = null

        private fun buildDataBase(context: Context): PrestamosDb {
            return Room.databaseBuilder(
                context.applicationContext,
                PrestamosDb::class.java,
                DATABASE_NAME
            ).build()
        }

        fun getInstance(context: Context): PrestamosDb {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context).also { instance = it }
            }
        }
    }
}