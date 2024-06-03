package ort.clases.parcial_22a_tp3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.domain.models.BestFlight
import ort.clases.parcial_22a_tp3.holder.BestFlightHolder
import ort.clases.parcial_22a_tp3.interfaces.OnClickNavigate

class BestFlightAdapter(
    var bestFlightsList: MutableList<BestFlight>,
    val onClickNavigate: OnClickNavigate
) : RecyclerView.Adapter<BestFlightHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestFlightHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.component_bf_search_result, parent, false)
        return BestFlightHolder(view)
    }

    override fun onBindViewHolder(holder: BestFlightHolder, position: Int) {
        val bestFlight = bestFlightsList[position]
        holder.setBestFlightPrice(bestFlight.price)
        holder.setTravelClass(bestFlight.flights[0].travelClass)
        holder.setDepartureName(bestFlight.flights[0].departureAirport.name)
        holder.setDepartureId(bestFlight.flights[0].departureAirport.id)
        holder.setArrivalId(bestFlight.flights[0].arrivalAirport.id)
        holder.setArrivalName(bestFlight.flights[0].arrivalAirport.name)
        holder.setTime(bestFlight.totalDuration)
        holder.setAirlineLogo(bestFlight.flights[0].airlineLogo)
        holder.setAirlineName(bestFlight.flights[0].airlineName)
//        holder.viewDetails().setOnClickListener {
//            onClickNavigate.toBestFlight(bestFlight)
//        }
    }

    override fun getItemCount() = bestFlightsList.size

    fun updateList(newList: MutableList<BestFlight>) {
        bestFlightsList = newList.toMutableList()
        notifyDataSetChanged()
    }
}