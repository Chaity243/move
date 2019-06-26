package com.chaity.easymove.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.chaity.easymove.domain.entity.Location
import com.google.gson.annotations.SerializedName

// This class is counterpart of Delivery entity in domain layer. This class is used by Room as well
// as retrofit to parse fetched data. We could have further created classes for Room
// and network parsing to separate the concerns. Right now we are mapping the database and network
// class exactly so I have left it like this.
@Entity(tableName = "delivery")
data class DeliveryData (
        @SerializedName("id")     @field:PrimaryKey  val id: Int,
        @SerializedName("description")      val description: String,
        @SerializedName("imageUrl")    val imageUrl: String,
        @SerializedName("location")   val location: Location
)
