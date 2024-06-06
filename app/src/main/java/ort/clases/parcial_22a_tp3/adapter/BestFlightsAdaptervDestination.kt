package ort.clases.parcial_22a_tp3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.databinding.ComponentCardItemExploreBinding
import ort.clases.parcial_22a_tp3.responses.BestFlightsResponse
import javax.inject.Inject

class BestFlightsAdaptervDestination @Inject constructor() :
    RecyclerView.Adapter<BestFlightsAdaptervDestination.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(val binding: ComponentCardItemExploreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BestFlightsResponse.BestFlight) {
            binding.apply {
                destinationname.text = item.flights?.last()?.arrivalAirport?.name.toString()
                flightNumber.text = item.flights?.last()?.flightNumber

                card.setOnClickListener {
                    onItemClickListener?.let { click ->
                        click(item)
                    }
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<BestFlightsResponse.BestFlight>() {
        override fun areItemsTheSame(oldItem: BestFlightsResponse.BestFlight, newItem: BestFlightsResponse.BestFlight): Boolean {
            return oldItem.departureToken == newItem.departureToken
        }

        override fun areContentsTheSame(oldItem: BestFlightsResponse.BestFlight, newItem: BestFlightsResponse.BestFlight): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ComponentCardItemExploreBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    private var onItemClickListener: ((BestFlightsResponse.BestFlight) -> Unit)? = null

    fun setOnItemCLickListener(listener: (BestFlightsResponse.BestFlight) -> Unit) {
        onItemClickListener = listener
    }
}
