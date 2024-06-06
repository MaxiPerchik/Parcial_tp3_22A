package ort.clases.parcial_22a_tp3.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ort.clases.parcial_22a_tp3.data.database.entities.FlightEntity

@Dao
interface FlightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlight(flight: FlightEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFlights(flights: List<FlightEntity>): List<Long>

    @Query("SELECT * FROM flights_table WHERE bFlightId = :bFlightId")
    suspend fun getFlightsByBFId(bFlightId: String): List<FlightEntity>
}