package ort.clases.parcial_22a_tp3.data.model

import com.google.gson.annotations.SerializedName
import ort.clases.parcial_22a_tp3.data.database.entities.AirportEntity
import ort.clases.parcial_22a_tp3.domain.models.Flight

data class FlightModel(
    @SerializedName("departure_airport") val departureAirport: AirportModel,
    @SerializedName("arrival_airport") val arrivalAirport: AirportModel,
    @SerializedName("airline") val airlineName: String,
    @SerializedName("airline_logo") val airlineLogo: String,
    @SerializedName("travel_class") val travelClass: String,
)

fun Flight.toAirportEntities(): List<AirportEntity> {
    return listOf(
        AirportEntity(
            id = this.departureAirport.id,
            name = this.departureAirport.name,
            time = this.departureAirport.time
        ),
        AirportEntity(
            id = this.arrivalAirport.id,
            name = this.arrivalAirport.name,
            time = this.arrivalAirport.time
        )
    )
}
