package ort.clases.parcial_22a_tp3.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ort.clases.parcial_22a_tp3.data.database.entities.AirportEntity

@Dao
interface AirportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirport(airport: AirportEntity): Long

    @Query("SELECT * FROM airports_table")
    suspend fun getAllAirports(): List<AirportEntity>

    @Query("SELECT * FROM airports_table WHERE id = :airportId")
    suspend fun getAirportById(airportId: String): AirportEntity
}