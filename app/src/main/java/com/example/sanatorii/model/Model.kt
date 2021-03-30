package com.example.sanatorii.model

import com.google.firebase.firestore.GeoPoint
import java.io.Serializable

data class Model(
    var images: String,
    var adress: String,
    var cost: Int,
    var fullInfo: String,
    var geo: GeoPoint,
    var info: String,
    var name: String,
    var rating: Float,
    var tel: String
): Serializable


