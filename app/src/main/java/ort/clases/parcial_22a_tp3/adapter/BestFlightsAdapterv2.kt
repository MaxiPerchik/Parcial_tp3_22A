package ort.clases.parcial_22a_tp3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.databinding.ComponentBfSearchResultBinding
import ort.clases.parcial_22a_tp3.databinding.FragmentSearchResultsBinding
import ort.clases.parcial_22a_tp3.responses.BestFlightsResponse
import javax.inject.Inject

class BestFlightsAdapterv2 @Inject constructor() :
    RecyclerView.Adapter<BestFlightsAdapterv2.ViewHolder>() {

    private lateinit var binding: ComponentBfSearchResultBinding
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ComponentBfSearchResultBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.set(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {
        fun set(item: BestFlightsResponse.BestFlight) {
            binding.apply {
                //duracion de vuelo
                include1.flightDuration.text = setTime(item.totalDuration)
                // logo
                val moviePosterURL = item.airlineLogo
                Glide.with(context).load(moviePosterURL).centerInside()
                    .into(include1.flightAirlineLogo)
                // airlinename
                include1.flightAirlineName.text = item.flights?.get(0)?.airline

                include2.flightArrivalID.text = item.flights?.last()?.arrivalAirport?.id
                include2.flightArrivalName.text = item.flights?.last()?.arrivalAirport?.name
                include2.flightDepartureID.text = item.flights?.get(0)?.departureAirport?.id
                include2.flightDepartureName.text = item.flights?.get(0)?.departureAirport?.name

                include3.flightTypeSeat.text = item.type.toString()
                include3.flightPrice.text = item.price.toString()
//                flight_departure__ID.text = item.flights?.last()?.arrivalAirport?.name
//                tvLang.text = item.airlineLogo
//                tvRate.text = item.flights?.last()?.flightNumber
//                tvMovieDateRelease.text = item.price.toString()
//                val moviePosterURL = item.airlineLogo
//                Glide.with(context).load(moviePosterURL).centerInside().into()

            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<BestFlightsResponse.BestFlight>() {
        override fun areItemsTheSame(
            oldItem: BestFlightsResponse.BestFlight,
            newItem: BestFlightsResponse.BestFlight
        ): Boolean {
            return oldItem.departureToken == newItem.departureToken
        }

        override fun areContentsTheSame(
            oldItem: BestFlightsResponse.BestFlight,
            newItem: BestFlightsResponse.BestFlight
        ): Boolean {
            return oldItem == newItem
        }

    }

    // AsyncListDiffer es una utilidad para calcular las diferencias entre dos listas de forma asíncrona
    val differ = AsyncListDiffer(this, differCallback)


    fun setTime(time: Int?): String {
        val hours = time?.div(60) // obtén las horas dividiendo por 60
        val minutes = time?.rem(60) // obtén los minutos tomando el módulo de 60
        return String.format("%02dhr %02dmin", hours, minutes)
    }
}