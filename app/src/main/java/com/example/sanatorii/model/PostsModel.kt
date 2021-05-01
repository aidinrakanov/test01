package com.example.sanatorii.model

import java.io.Serializable

data class PostsModel(
    var title: String = "",
    var desc: String = "",
    var image: String = ""
) : Serializable