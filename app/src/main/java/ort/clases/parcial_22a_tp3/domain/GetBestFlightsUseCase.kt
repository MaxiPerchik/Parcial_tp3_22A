package ort.clases.parcial_22a_tp3.domain

import ort.clases.parcial_22a_tp3.data.FlightRepository
import ort.clases.parcial_22a_tp3.data.database.dao.AirportDao
import ort.clases.parcial_22a_tp3.data.database.dao.BestFlightDao
import ort.clases.parcial_22a_tp3.data.database.dao.FlightDao
import ort.clases.parcial_22a_tp3.data.database.entities.AirportEntity
import ort.clases.parcial_22a_tp3.data.database.entities.BestFlightEntity
import ort.clases.parcial_22a_tp3.data.database.entities.FlightEntity
import ort.clases.parcial_22a_tp3.data.database.entities.toDomainModel
import ort.clases.parcial_22a_tp3.data.model.AirportModel
import ort.clases.parcial_22a_tp3.data.model.BestFlightModel
import ort.clases.parcial_22a_tp3.domain.models.BestFlight
import javax.inject.Inject
import android.util.Log

class GetBestFlightsUseCase @Inject constructor(
    private val repository: FlightRepository,
    private val bestFlightDao: BestFlightDao,
    private val flightDao: FlightDao,
    private val airportDao: AirportDao
) {

    suspend fun invoke(): List<BestFlight> {
        val bestFlights = repository.getAllBestFlightsFromApi()
        return if (bestFlights.isNullOrEmpty()) {
            val insertedBestFlights = insertBestFlights(bestFlights)
            val allAirports = repository.getAllAirports()

            insertedBestFlights.map { bflight ->
                val flights = repository.getFlightsForBestFlight(bflight.bFlightId)
                bflight.toDomainModel(flights, allAirports)
            }
        } else {
            repository.getAllBFlightsFromDB()
        }
    }

    private suspend fun insertBestFlights(bestFlights: List<BestFlightModel>): List<BestFlightEntity> {
        val insertedEntities = mutableListOf<BestFlightEntity>()
        bestFlights.forEach { bflight ->
            val id = bflight.departureToken
            val bestFlightEntitiy = BestFlightEntity(
                bFlightId = id,
                totalDuration = bflight.totalDuration,
                price = bflight.price,
                departureToken = bflight.departureToken,
                type = bflight.type,
                isFav = false
            )
            // Insert the best flights into the DB
            bestFlightDao.insertBestFlight(bestFlightEntitiy)

            // Iterate over all flights in the best flights
            val flightEntities = bflight.flights.mapNotNull { flight ->
                if (flight.departureAirport == null || flight.arrivalAirport == null) {
                    Log.e("GetBestFlightsUseCase", "Null airport found for flight $flight")
                    null
                } else {
                    val departureAirportId = insertAirport(flight.departureAirport)
                    val arrivalAirportId = insertAirport(flight.arrivalAirport)
                    FlightEntity(
                        bFlightId = id,
                        airlineName = flight.airlineName,
                        airlineLogo = flight.airlineLogo,
                        travelClass = flight.travelClass,
                        departureAirportId = departureAirportId,
                        arrivalAirportId = arrivalAirportId,
                    )
                }
            }
            // Insert the flight entities into the DB
            flightDao.insertAllFlights(flightEntities)

            insertedEntities.add(bestFlightEntitiy)
        }
        return insertedEntities
    }

    private suspend fun insertAirport(airport: AirportModel): String {
        val airportEntity = AirportEntity(
            id = airport.id,
            name = airport.name,
            time = airport.time
        )
        airportDao.insertAirport(airportEntity)
        return airport.id
    }

    suspend fun save(bestFlight: BestFlight) {
        val bestFlightDB = bestFlightDao.getBestFlight(bestFlight.bFlightId)
        if (bestFlightDB.isFav) {
            bestFlightDao.saveBFlightOnDB(bestFlight.bFlightId, false)
        }else{
            bestFlightDao.saveBFlightOnDB(bestFlight.bFlightId, true)
        }

    }
}
