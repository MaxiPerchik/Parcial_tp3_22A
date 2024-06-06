package ort.clases.parcial_22a_tp3.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ort.clases.parcial_22a_tp3.data.database.Database
import ort.clases.parcial_22a_tp3.data.database.dao.AirportDao
import ort.clases.parcial_22a_tp3.data.database.dao.BestFlightDao
import ort.clases.parcial_22a_tp3.data.database.dao.FlightDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val  BEST_FLIGHT_DATABASE_NAME = "best_flight_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java, BEST_FLIGHT_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideBestFlightDao(db: Database): BestFlightDao = db.bestFlightDao()

    @Singleton
    @Provides
    fun provideFlightDao(db: Database): FlightDao = db.flightDao()

    @Singleton
    @Provides
    fun provideAirportDao(db: Database): AirportDao = db.airportDao()

}