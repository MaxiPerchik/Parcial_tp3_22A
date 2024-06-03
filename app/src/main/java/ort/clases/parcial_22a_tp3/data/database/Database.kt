package ort.clases.parcial_22a_tp3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ort.clases.parcial_22a_tp3.data.database.dao.AirportDao
import ort.clases.parcial_22a_tp3.data.database.dao.BestFlightDao
import ort.clases.parcial_22a_tp3.data.database.dao.FlightDao
import ort.clases.parcial_22a_tp3.data.database.entities.AirportEntity
import ort.clases.parcial_22a_tp3.data.database.entities.BestFlightEntity
import ort.clases.parcial_22a_tp3.data.database.entities.FlightEntity

@Database(entities = [BestFlightEntity::class, FlightEntity::class, AirportEntity::class], version = 3)
abstract class Database : RoomDatabase() {
    abstract fun bestFlightDao(): BestFlightDao
    abstract fun flightDao(): FlightDao
    abstract fun airportDao(): AirportDao


}