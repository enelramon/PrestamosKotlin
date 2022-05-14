package com.sagrd.prestamoskotlin.di

import android.content.Context
import androidx.room.Room
import com.sagrd.prestamoskotlin.data.OcupacionDao
import com.sagrd.prestamoskotlin.data.OcupacionRepository
import com.sagrd.prestamoskotlin.data.PrestamosDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


        @Singleton
        @Provides
        fun ProvideTicketDb(@ApplicationContext context: Context): PrestamosDb {
          val DATABASE_NAME = "PrestamosDb"
            return Room.databaseBuilder(
                context,
                PrestamosDb::class.java,
                DATABASE_NAME       )
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        fun ProvideOcupacionDAO(prestamosDb: PrestamosDb): OcupacionDao {
            return prestamosDb.ocupacionDao
        }

    @Provides
    fun ProvideOcupacionRepository(ocupacionDao: OcupacionDao): OcupacionRepository {
        return OcupacionRepository(ocupacionDao)
    }
}