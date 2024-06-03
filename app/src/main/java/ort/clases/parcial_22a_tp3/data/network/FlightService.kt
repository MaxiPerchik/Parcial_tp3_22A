package ort.clases.parcial_22a_tp3.data.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ort.clases.parcial_22a_tp3.data.model.BestFlightModel
import javax.inject.Inject

class FlightService @Inject constructor(
    private val service: FlightApiClient
) {

    suspend fun getBestFlights(): List<BestFlightModel> {
        return withContext(Dispatchers.IO) {
            val response = try {
                service.getFlights()
            } catch (e: Exception) {
                Log.e("Service", "error de conexion a la api")
                null
            }
            if (response != null && response.isSuccessful) {
                val apiResponse = response.body()
                apiResponse?.bestFlights?.map { bflight ->
                    BestFlightModel(
                        totalDuration = bflight.totalDuration,
                        price = bflight.price,
                        departureToken = bflight.departureToken,
                        type = bflight.type,
                        flights = bflight.flights
                    )
                } ?: emptyList()

            } else {
                emptyList()
            }
        }
    }
}