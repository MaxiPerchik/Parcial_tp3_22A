package ort.clases.parcial_22a_tp3.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ort.clases.parcial_22a_tp3.domain.models.Airport

@Entity(tableName = "airports_table")
data class AirportEntity(
    @PrimaryKey val id: String,
    val name: String,
    val time: String,
)

fun AirportEntity.toDomainModel() = Airport(
    id = id,
    name = name,
    time = time
)