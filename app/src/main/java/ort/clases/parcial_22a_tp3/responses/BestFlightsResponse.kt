package ort.clases.parcial_22a_tp3.responses

import com.google.gson.annotations.SerializedName

data class BestFlightsResponse(
    @SerializedName("best_flights")
    val bestFlights: List<BestFlight?>?,
    @SerializedName("other_flights")
    val otherFlights: List<OtherFlight?>?,
    @SerializedName("price_insights")
    val priceInsights: PriceInsights?,
    @SerializedName("search_metadata")
    val searchMetadata: SearchMetadata?,
    @SerializedName("search_parameters")
    val searchParameters: SearchParameters?
) {
    data class BestFlight(
        @SerializedName("airline_logo")
        val airlineLogo: String?,
        @SerializedName("carbon_emissions")
        val carbonEmissions: CarbonEmissions?,
        @SerializedName("departure_token")
        val departureToken: String?,
        @SerializedName("flights")
        val flights: List<Flight?>?,
        @SerializedName("layovers")
        val layovers: List<Layover?>?,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("total_duration")
        val totalDuration: Int?,
        @SerializedName("type")
        val type: String?
    ) {
        data class CarbonEmissions(
            @SerializedName("difference_percent")
            val differencePercent: Int?,
            @SerializedName("this_flight")
            val thisFlight: Int?,
            @SerializedName("typical_for_this_route")
            val typicalForThisRoute: Int?
        )

        data class Flight(
            @SerializedName("airline")
            val airline: String?,
            @SerializedName("airline_logo")
            val airlineLogo: String?,
            @SerializedName("airplane")
            val airplane: String?,
            @SerializedName("arrival_airport")
            val arrivalAirport: ArrivalAirport?,
            @SerializedName("departure_airport")
            val departureAirport: DepartureAirport?,
            @SerializedName("duration")
            val duration: Int?,
            @SerializedName("extensions")
            val extensions: List<String?>?,
            @SerializedName("flight_number")
            val flightNumber: String?,
            @SerializedName("legroom")
            val legroom: String?,
            @SerializedName("often_delayed_by_over_30_min")
            val oftenDelayedByOver30Min: Boolean?,
            @SerializedName("overnight")
            val overnight: Boolean?,
            @SerializedName("travel_class")
            val travelClass: String?
        ) {
            data class ArrivalAirport(
                @SerializedName("id")
                val id: String?,
                @SerializedName("name")
                val name: String?,
                @SerializedName("time")
                val time: String?
            )

            data class DepartureAirport(
                @SerializedName("id")
                val id: String?,
                @SerializedName("name")
                val name: String?,
                @SerializedName("time")
                val time: String?
            )
        }

        data class Layover(
            @SerializedName("duration")
            val duration: Int?,
            @SerializedName("id")
            val id: String?,
            @SerializedName("name")
            val name: String?
        )
    }

    data class OtherFlight(
        @SerializedName("airline_logo")
        val airlineLogo: String?,
        @SerializedName("carbon_emissions")
        val carbonEmissions: CarbonEmissions?,
        @SerializedName("departure_token")
        val departureToken: String?,
        @SerializedName("flights")
        val flights: List<Flight?>?,
        @SerializedName("layovers")
        val layovers: List<Layover?>?,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("total_duration")
        val totalDuration: Int?,
        @SerializedName("type")
        val type: String?
    ) {
        data class CarbonEmissions(
            @SerializedName("difference_percent")
            val differencePercent: Int?,
            @SerializedName("this_flight")
            val thisFlight: Int?,
            @SerializedName("typical_for_this_route")
            val typicalForThisRoute: Int?
        )

        data class Flight(
            @SerializedName("airline")
            val airline: String?,
            @SerializedName("airline_logo")
            val airlineLogo: String?,
            @SerializedName("airplane")
            val airplane: String?,
            @SerializedName("arrival_airport")
            val arrivalAirport: ArrivalAirport?,
            @SerializedName("departure_airport")
            val departureAirport: DepartureAirport?,
            @SerializedName("duration")
            val duration: Int?,
            @SerializedName("extensions")
            val extensions: List<String?>?,
            @SerializedName("flight_number")
            val flightNumber: String?,
            @SerializedName("legroom")
            val legroom: String?,
            @SerializedName("overnight")
            val overnight: Boolean?,
            @SerializedName("travel_class")
            val travelClass: String?
        ) {
            data class ArrivalAirport(
                @SerializedName("id")
                val id: String?,
                @SerializedName("name")
                val name: String?,
                @SerializedName("time")
                val time: String?
            )

            data class DepartureAirport(
                @SerializedName("id")
                val id: String?,
                @SerializedName("name")
                val name: String?,
                @SerializedName("time")
                val time: String?
            )
        }

        data class Layover(
            @SerializedName("duration")
            val duration: Int?,
            @SerializedName("id")
            val id: String?,
            @SerializedName("name")
            val name: String?
        )
    }

    data class PriceInsights(
        @SerializedName("lowest_price")
        val lowestPrice: Int?,
        @SerializedName("price_history")
        val priceHistory: List<List<Int?>?>?,
        @SerializedName("price_level")
        val priceLevel: String?,
        @SerializedName("typical_price_range")
        val typicalPriceRange: List<Int?>?
    )

    data class SearchMetadata(
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("google_flights_url")
        val googleFlightsUrl: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("json_endpoint")
        val jsonEndpoint: String?,
        @SerializedName("prettify_html_file")
        val prettifyHtmlFile: String?,
        @SerializedName("processed_at")
        val processedAt: String?,
        @SerializedName("raw_html_file")
        val rawHtmlFile: String?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("total_time_taken")
        val totalTimeTaken: Double?
    )

    data class SearchParameters(
        @SerializedName("arrival_id")
        val arrivalId: String?,
        @SerializedName("departure_id")
        val departureId: String?,
        @SerializedName("engine")
        val engine: String?,
        @SerializedName("gl")
        val gl: String?,
        @SerializedName("hl")
        val hl: String?,
        @SerializedName("outbound_date")
        val outboundDate: String?,
        @SerializedName("return_date")
        val returnDate: String?
    )
}
