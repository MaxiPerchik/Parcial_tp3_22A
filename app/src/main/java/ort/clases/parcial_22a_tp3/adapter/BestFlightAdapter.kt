package ort.clases.parcial_22a_tp3.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.domain.models.BestFlight
import ort.clases.parcial_22a_tp3.holder.BestFlightHolder
import ort.clases.parcial_22a_tp3.interfaces.OnClickNavigate
import kotlin.math.log

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
        holder.setNameAirline(bestFlight.flights[0].airlineName)

        holder.viewDetails().setOnClickListener {
            onClickNavigate.toBestFlight(bestFlight)
        }
    }

    override fun getItemCount() = bestFlightsList.size

    fun updateList(newList: MutableList<BestFlight>) {
        bestFlightsList = newList.toMutableList()
        notifyDataSetChanged()
    }
}