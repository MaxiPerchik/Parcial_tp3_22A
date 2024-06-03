package ort.clases.parcial_22a_tp3.holder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.R

class BestFlightHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v

    fun setBestFlightPrice(price: Double?) {

        val itemPrice = view.findViewById<TextView>(R.id.flight_price)
        itemPrice.text = "$" + price.toString()
    }

    fun setNameAirline(airlineName: String?) {
        val itemAirlineName = view.findViewById<TextView>(R.id.airport_name)
        itemAirlineName.text = airlineName
    }


    fun viewDetails() = view.findViewById<Button>(R.id.go_to_details)
}