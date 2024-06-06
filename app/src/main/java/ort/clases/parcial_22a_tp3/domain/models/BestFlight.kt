package ort.clases.parcial_22a_tp3.domain.models

import android.os.Parcel
import android.os.Parcelable

data class BestFlight(
    val flights: List<Flight>,
    val totalDuration: Int,
    val price: Double,
    val bFlightId: String,
    val departureToken: String,
    val type: String,
    val isFav: Boolean = false,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Flight.CREATOR)!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(flights)
        parcel.writeInt(totalDuration)
        parcel.writeDouble(price)
        parcel.writeString(bFlightId)
        parcel.writeString(departureToken)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BestFlight> {
        override fun createFromParcel(parcel: Parcel): BestFlight {
            return BestFlight(parcel)
        }

        override fun newArray(size: Int): Array<BestFlight?> {
            return arrayOfNulls(size)
        }
    }
}