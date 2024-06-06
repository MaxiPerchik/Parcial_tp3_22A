package ort.clases.parcial_22a_tp3.domain.models

data class Offer(
    val discountText: String,
    val cardType: String,
    val description: String,
    val imageRes: Int,
    val isLimitedOffer: Boolean = false
) {
}