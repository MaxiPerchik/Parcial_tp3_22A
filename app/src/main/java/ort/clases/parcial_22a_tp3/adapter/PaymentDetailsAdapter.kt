package ort.clases.parcial_22a_tp3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ort.clases.parcial_22a_tp3.R
import ort.clases.parcial_22a_tp3.domain.models.PaymentDetail
import ort.clases.parcial_22a_tp3.interfaces.OnPaymentDetailClickListener
import ort.clases.parcial_22a_tp3.holder.PaymentDetailsHolder

// Adaptador para la lista de detalles de pago
class PaymentDetailsAdapter(
    private val paymentDetails: List<PaymentDetail>, // Lista de detalles de pago
    private val listener: OnPaymentDetailClickListener // Interfaz de escucha para manejar los clics
) : RecyclerView.Adapter<PaymentDetailsHolder>() {

    // Método para inflar el diseño del elemento de la lista y crear un Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentDetailsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.component_payment_item, parent, false)
        return PaymentDetailsHolder(view)
    }

    // Método para enlazar los datos de un elemento de la lista con el Holder correspondiente
    override fun onBindViewHolder(holder: PaymentDetailsHolder, position: Int) {
        val paymentDetail = paymentDetails[position]
        holder.bind(paymentDetail, listener)

        // mostrar u ocultar el tag
        if (paymentDetail.isNew) {
            holder.newTag.visibility = View.VISIBLE
        } else {
            holder.newTag.visibility = View.GONE
        }
    }

    // Método que devuelve el número de elementos en la lista
    override fun getItemCount(): Int = paymentDetails.size

}
