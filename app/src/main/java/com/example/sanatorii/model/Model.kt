package com.example.sanatorii.model

import com.google.firebase.firestore.GeoPoint
import java.io.Serializable

data class Model(
    var adress: String? = null,
    var cost: Int? = null,
    var fullInfo: String? = null,
    var geoPosition: GeoPoint? = null,
    var image: String? = null,
    var info: String? = null,
    var name: String? = null,
    var rating: Float? = null,
    var comment: String? = null,
    var telephone: String? = null
) : Serializable


