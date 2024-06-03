package ort.clases.parcial_22a_tp3.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ort.clases.parcial_22a_tp3.domain.models.BestFlight

@Entity(tableName = "bests_flights_table")
data class BestFlightEntity(
    @PrimaryKey var bFlightId: String,
    val totalDuration: Int,
    val price: Double,
    val departureToken: String,
    val type: String,
    val isFav: Boolean
)

fun BestFlightEntity.toDomainModel(
    flights: List<FlightEntity>,
    airports: Map<String, AirportEntity>
): BestFlight {
    val domainFlight = flights.map { flight ->
        flight.toDomainModel(airports)
    }
    return BestFlight(
        flights = domainFlight,
        totalDuration = totalDuration,
        price = price,
        bFlightId = bFlightId,
        departureToken = departureToken,
        type = type,
        isFav = isFav
    )
}