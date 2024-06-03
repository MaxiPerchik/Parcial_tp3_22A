package ort.clases.parcial_22a_tp3.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponseModel(@SerializedName("best_flights") val bestFlights: List<BestFlightModel>)