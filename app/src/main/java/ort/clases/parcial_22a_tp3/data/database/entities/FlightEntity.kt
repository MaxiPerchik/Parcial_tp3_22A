package ort.clases.parcial_22a_tp3.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ort.clases.parcial_22a_tp3.domain.models.Flight

@Entity(
    tableName = "flights_table", foreignKeys = [
        ForeignKey(
            entity = BestFlightEntity::class,
            parentColumns = ["bFlightId"],
            childColumns = ["bFlightId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FlightEntity(
    @PrimaryKey val bFlightId: String,
    val airlineName: String,
    val airlineLogo: String,
    val travelClass: String,
    val departureAirportId: String,
    val arrivalAirportId: String,
)

fun FlightEntity.toDomainModel(airports: Map<String, AirportEntity>): Flight {
    val departureAirport =
        airports[departureAirportId] ?: throw IllegalStateException("Departure airport not found")
    val arrivalAirport =
        airports[arrivalAirportId] ?: throw IllegalStateException("Arrival airport not found")
    return Flight(
        departureAirport = departureAirport.toDomainModel(),
        arrivalAirport = arrivalAirport.toDomainModel(),
        airlineName = airlineName,
        airlineLogo = airlineLogo,
        travelClass = travelClass
    )
}