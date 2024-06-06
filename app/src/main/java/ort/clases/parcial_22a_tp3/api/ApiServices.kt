package ort.clases.parcial_22a_tp3.api

import ort.clases.parcial_22a_tp3.responses.BestFlightsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("search?engine=google_flights&api_key=123&arrival_id=MIA")
    fun getBestFlightsList(): Call<BestFlightsResponse>
}