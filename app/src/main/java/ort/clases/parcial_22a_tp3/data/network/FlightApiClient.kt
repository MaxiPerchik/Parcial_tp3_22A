package ort.clases.parcial_22a_tp3.data.network

import ort.clases.parcial_22a_tp3.data.model.ApiResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface FlightApiClient {

    // devuelve una response
    @GET("search?engine=google_flights&api_key=123&departure_id=EZE&arrival_id=MIA&outbound_date=2024-05-31&return_date=2024-06-10")
    suspend fun getFlights(): Response<ApiResponseModel>
}