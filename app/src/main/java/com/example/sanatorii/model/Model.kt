package com.example.sanatorii.model

import com.google.firebase.firestore.GeoPoint
import java.io.Serializable

data class Model(
    var adress: String="",
    var cost: Int? = 0,
    var fullInfo: String="",
    var geoPosition: GeoPoint? = null,
    var image: String="",
    var info: String="",
    var name: String="",
    var rating: Float? = 0.0f,
    var telephone: String=""
): Serializable


