package ort.clases.parcial_22a_tp3.interfaces

import ort.clases.parcial_22a_tp3.domain.models.BestFlight
import ort.clases.parcial_22a_tp3.domain.models.PaymentDetail

interface OnClickNavigate {
    fun toBestFlight(bestFlight: BestFlight)

}