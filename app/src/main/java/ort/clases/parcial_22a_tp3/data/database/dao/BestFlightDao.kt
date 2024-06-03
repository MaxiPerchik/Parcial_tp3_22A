package ort.clases.parcial_22a_tp3.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ort.clases.parcial_22a_tp3.data.database.entities.BestFlightEntity

@Dao
interface BestFlightDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBestFlight(bflight: BestFlightEntity): Long

    @Query("SELECT * FROM bests_flights_table WHERE bFlightId = :bFlightId")
    suspend fun getBestFlight(bFlightId: String): BestFlightEntity

    @Transaction
    @Query("SELECT * FROM bests_flights_table")
    suspend fun getAllBFlights(): List<BestFlightEntity>

    @Query("DELETE FROM bests_flights_table WHERE bFlightId = :bFlightId")
    suspend fun deleteBFlights(bFlightId: String)

    @Query("DELETE FROM bests_flights_table")
    suspend fun deleteAllBFs()

    @Query("UPDATE bests_flights_table SET isFav = :isFav WHERE bFlightId = :bFlightId")
    suspend fun saveBFlightOnDB(bFlightId: String, isFav: Boolean)
}