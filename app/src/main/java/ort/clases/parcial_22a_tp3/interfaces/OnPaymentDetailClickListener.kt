package ort.clases.parcial_22a_tp3.interfaces

import ort.clases.parcial_22a_tp3.data.models.PaymentDetail

interface OnPaymentDetailClickListener {
    fun onPaymentDetailClick(paymentDetail: PaymentDetail)
}