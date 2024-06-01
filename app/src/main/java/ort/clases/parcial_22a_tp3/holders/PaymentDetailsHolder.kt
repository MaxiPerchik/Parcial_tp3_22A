package ort.clases.parcial_22a_tp3.holders

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.data.models.PaymentDetail
import ort.clases.parcial_22a_tp3.interfaces.OnPaymentDetailClickListener

class PaymentDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.payment_img)
    private val textView: TextView = itemView.findViewById(R.id.payment_text)
    val newTag: LinearLayout = itemView.findViewById(R.id.new_tag)

    // Método para enlazar los datos de un PaymentDetail con las vistas del ViewHolder
    fun bind(paymentDetail: PaymentDetail, listener: OnPaymentDetailClickListener) {
        imageView.setImageResource(paymentDetail.imageResId) // Configura la imagen del ImageView
        textView.text = paymentDetail.text // Configura el texto del TextView
        // Configura el listener para manejar el clic en el elemento de la lista
        itemView.setOnClickListener {
            listener.onPaymentDetailClick(paymentDetail)
        }
    }

}