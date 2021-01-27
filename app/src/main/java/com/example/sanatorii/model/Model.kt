package com.example.sanatorii.model

import android.media.Rating
import java.io.Serializable

data class Model(
    var images: String,
    var name: String,
    var adress: String,
    var info: String,
    var cost: Int,
    var rating: Float,
    var fullInfo: String
): Serializable


