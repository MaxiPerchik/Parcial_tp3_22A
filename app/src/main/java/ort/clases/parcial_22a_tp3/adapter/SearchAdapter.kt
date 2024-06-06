import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.domain.models.SearchOfferItem

class SearchAdapter(
    private val SearchoffersList: List<SearchOfferItem>
) : RecyclerView.Adapter<SearchAdapter.OfferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.search_component_offer_item, parent, false)
        return OfferViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val context = holder.itemView.context
        val searchOffer = SearchoffersList[position]

        // Aquí se utiliza SpannableString para aplicar estilo negrita a parte del texto.
        val spannable = SpannableString(searchOffer.discountText)
        // Asumiendo que quieres poner en negrita hasta el primer 'for' o 'with'
        val end = searchOffer.discountText.indexOf(" for").takeIf { it > -1 } ?: searchOffer.discountText.indexOf(" with").takeIf { it > -1 } ?: searchOffer.discountText.length
        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        holder.textViewDiscount.text = spannable
        holder.textViewOffer.text = searchOffer.offerText
        holder.imageView.setImageResource(searchOffer.imageResourceId)
    }
    override fun getItemCount(): Int {
        return SearchoffersList.size
    }

    class OfferViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.imageViewSearchOffers)
        var textViewDiscount: TextView = view.findViewById(R.id.SearchTextOfferViewDiscount)
        var textViewOffer: TextView = view.findViewById(R.id.SearchTextViewOffer)
    }
}

