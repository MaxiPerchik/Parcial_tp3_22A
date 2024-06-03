package ort.clases.parcial_22a_tp3.data

import ort.clases.parcial_22a_tp3.data.database.dao.AirportDao
import ort.clases.parcial_22a_tp3.data.database.dao.BestFlightDao
import ort.clases.parcial_22a_tp3.data.database.dao.FlightDao
import ort.clases.parcial_22a_tp3.data.database.entities.toDomainModel
import ort.clases.parcial_22a_tp3.data.database.entities.AirportEntity
import ort.clases.parcial_22a_tp3.data.database.entities.FlightEntity
import ort.clases.parcial_22a_tp3.data.model.BestFlightModel
import ort.clases.parcial_22a_tp3.data.network.FlightService
import ort.clases.parcial_22a_tp3.domain.models.BestFlight
import javax.inject.Inject

class FlightRepository @Inject constructor(
    private val service: FlightService,
    private val bfDao: BestFlightDao,
    private val fDao: FlightDao,
    private val aDao: AirportDao,
) {
    suspend fun getAllBestFlightsFromApi(): List<BestFlightModel> {
        return service.getBestFlights()
    }

    /**
     *  Trae los best flights de la DB (crea la relacion entre aeropuertos flights y bf)
     */
    suspend fun getAllBFlightsFromDB(): List<BestFlight> {
        val bflights = bfDao.getAllBFlights()
        val airports = getAllAirports()

        return bflights.map { bflight ->
            val flights = getFlightsForBestFlight(bflight.bFlightId)
            bflight.toDomainModel(flights, airports)
        }
    }

    suspend fun getAllAirports(): Map<String, AirportEntity> {
        return aDao.getAllAirports().associateBy { it.id }
    }


    suspend fun getFlightsForBestFlight(id: String): List<FlightEntity> {
        return fDao.getFlightsByBFId(id)
    }

}