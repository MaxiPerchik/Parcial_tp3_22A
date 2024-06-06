package ort.clases.parcial_22a_tp3.holder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ort.clases.parcial_22a_tp3.R

class BestFlightHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v

    fun setBestFlightPrice(price: Double?) {

        val itemPrice = view.findViewById<TextView>(R.id.flight_price)
        itemPrice.text = "$" + price.toString()
    }
    fun setDepartureId(departureId: String?) {
        view.findViewById<TextView>(R.id.flight_departure__ID).text = departureId
    }

    fun setDepartureName(departureName: String?) {
        view.findViewById<TextView>(R.id.flight_departure__name).text = departureName
    }

    fun setArrivalId(arrivalId: String?) {
        view.findViewById<TextView>(R.id.flight_arrival__ID).text = arrivalId
    }

    fun setArrivalName(arrivalName: String?) {
        view.findViewById<TextView>(R.id.flight_arrival__name).text = arrivalName
    }

    fun setTravelClass(travelClass: String?) {
        val itemTravelClass = view.findViewById<TextView>(R.id.flight_type_seat)
        itemTravelClass.text = travelClass
    }
    fun setTime(time: Int?){
        val hours = time?.div(60) // obtén las horas dividiendo por 60
        val minutes = time?.rem(60) // obtén los minutos tomando el módulo de 60
        val time = String.format("%02dhr %02dmin", hours, minutes)
       view.findViewById<TextView>(R.id.flight_duration).text = time
    }
    fun setAirlineName(airlineName: String?) {
        view.findViewById<TextView>(R.id.flight_airline_name).text = airlineName
    }

    fun setAirlineLogo(airlineLogo: String?) {
        val itemAirlineLogo = view.findViewById<ImageView>(R.id.flight_airline_logo)
        Glide.with(view.context).load(airlineLogo).centerInside().into(itemAirlineLogo)
    }
    fun viewDetails() = view.findViewById<Button>(R.id.go_to_details)
}