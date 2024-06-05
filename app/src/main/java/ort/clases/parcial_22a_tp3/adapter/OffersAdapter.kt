package ort.clases.parcial_22a_tp3.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.data.models.Offer

class OffersAdapter(private val offers: List<Offer>) : RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_offer_item, parent, false)
        return OffersViewHolder(view)
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        val offer = offers[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int = offers.size

    class OffersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val offerTitle: TextView = itemView.findViewById(R.id.offer_title)
        private val offerDescription: TextView = itemView.findViewById(R.id.offer_description)
        private val offerImage: ImageView = itemView.findViewById(R.id.offer_image)
        private val offerArrow: ImageView = itemView.findViewById(R.id.offer_arrow)

        fun bind(offer: Offer) {
            val title = "<b>${offer.discountText}</b> with your ${offer.cardType} credit cards"
            offerTitle.text = Html.fromHtml(title)
            offerDescription.text = offer.description
            offerImage.setImageResource(offer.imageRes)
            offerArrow.setImageResource(R.drawable.arrow_next) // Ajusta según tu recurso
        }
    }
}
