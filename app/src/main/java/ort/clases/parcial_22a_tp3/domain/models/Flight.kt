package ort.clases.parcial_22a_tp3.domain.models

import android.os.Parcel
import android.os.Parcelable

data class Flight(
    val departureAirport : Airport,
    val arrivalAirport : Airport,
    val airlineName: String,
    val airlineLogo: String,
    val travelClass: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Airport::class.java.classLoader)!!,
        parcel.readParcelable(Airport::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(departureAirport, flags)
        parcel.writeParcelable(arrivalAirport, flags)
        parcel.writeString(airlineName)
        parcel.writeString(airlineLogo)
        parcel.writeString(travelClass)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Flight> {
        override fun createFromParcel(parcel: Parcel): Flight {
            return Flight(parcel)
        }

        override fun newArray(size: Int): Array<Flight?> {
            return arrayOfNulls(size)
        }
    }
}