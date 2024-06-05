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


class OffersAdapter(private val offers: List<Offer>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_REGULAR = 0
        private const val TYPE_LIMITED = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (offers[position].isLimitedOffer) TYPE_LIMITED else TYPE_REGULAR
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_LIMITED) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.component_offer_item_limited, parent, false)
            LimitedOfferViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.component_offer_item, parent, false)
            RegularOfferViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val offer = offers[position]
        if (holder is LimitedOfferViewHolder) {
            holder.bind(offer)
        } else if (holder is RegularOfferViewHolder) {
            holder.bind(offer)
        }
    }

    override fun getItemCount(): Int = offers.size

    class RegularOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val offerTitle: TextView = itemView.findViewById(R.id.offer_title)
        private val offerDescription: TextView = itemView.findViewById(R.id.offer_description)
        private val offerImage: ImageView = itemView.findViewById(R.id.offer_image)
        private val offerArrow: ImageView = itemView.findViewById(R.id.offer_arrow)

        fun bind(offer: Offer) {
            val title = "<b>${offer.discountText}</b> with your ${offer.cardType} credit cards"
            offerTitle.text = Html.fromHtml(title)
            offerDescription.text = offer.description
            offerImage.setImageResource(offer.imageRes)
            offerArrow.setImageResource(R.drawable.arrow_next)


        }
    }

    class LimitedOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val limitedOfferLabel: TextView = itemView.findViewById(R.id.limited_offer_label)
        private val offerTitle: TextView = itemView.findViewById(R.id.offer_title)
        private val offerDescription: TextView = itemView.findViewById(R.id.offer_description)
        private val offerImage: ImageView = itemView.findViewById(R.id.offer_image)
        private val offerArrow: ImageView = itemView.findViewById(R.id.offer_arrow)
        private val offerFavorite: ImageView = itemView.findViewById(R.id.offer_favorite)

        fun bind(offer: Offer) {
            val title = "<b>${offer.discountText}</b> with your ${offer.cardType} credit cards"
            offerTitle.text = Html.fromHtml(title)
            offerDescription.text = offer.description
            offerImage.setImageResource(offer.imageRes)
            offerArrow.setImageResource(R.drawable.arrow_next)
            offerFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp) //
            limitedOfferLabel.visibility = if (offer.isLimitedOffer) View.VISIBLE else View.GONE


            // Añadir OnClickListener para cambiar el icono de favorito
            offerFavorite.setOnClickListener {
                val isFavorite = offerFavorite.tag as? Boolean ?: false
                if (isFavorite) {
                    offerFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                } else {
                    offerFavorite.setImageResource(R.drawable.ic_favorite_full_24dp)
                }
                offerFavorite.tag = !isFavorite
            }
        }
    }
}
