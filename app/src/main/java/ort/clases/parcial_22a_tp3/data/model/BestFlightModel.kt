package ort.clases.parcial_22a_tp3.data.model

import com.google.gson.annotations.SerializedName

data class BestFlightModel(
    val flights: List<FlightModel>,
    @SerializedName("total_duration") val totalDuration: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("departure_token") val departureToken: String,
    @SerializedName("type") val type: String
)
fun BestFlightModel.toDomain() = BestFlightModel(flights, totalDuration, price, departureToken, type)
