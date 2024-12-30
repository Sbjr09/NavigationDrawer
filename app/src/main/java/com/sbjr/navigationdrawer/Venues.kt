package com.sbjr.navigationdrawer

data class Venues(
    val id: String,
    val name: String,
    val location: Location
)

data class Location(
    val address: String?,
    val lat: Double,
    val lng: Double,
    val distance: Int,
    val city: String?,
    val state: String?,
    val country: String?
)
