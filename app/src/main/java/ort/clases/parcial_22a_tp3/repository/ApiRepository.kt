package ort.clases.parcial_22a_tp3.repository

import dagger.hilt.android.scopes.ActivityScoped
import ort.clases.parcial_22a_tp3.api.ApiServices
import javax.inject.Inject

@ActivityScoped
class ApiRepository @Inject constructor(
    private val apiServices: ApiServices // Inyecta la instancia de ApiServices
) {
    // Método para obtener la lista de películas populares, delega la llamada al servicio API
    fun getBestFlights() = apiServices.getBestFlightsList()
}