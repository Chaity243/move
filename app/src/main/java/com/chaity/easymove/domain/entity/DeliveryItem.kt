package com.chaity.easymove.domain.entity

data class DeliveryItem (
        val id: Int,
        val description: String,
        val imageUrl: String,
        val location: Location
)
