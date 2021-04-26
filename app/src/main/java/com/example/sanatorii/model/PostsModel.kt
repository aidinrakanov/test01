package com.example.sanatorii.model

import java.io.Serializable

data class PostsModel(
    var post_image: String = "",
    var post_title: String = "",
    var post_descr: String = "",
    var post_date: String = ""
        ) : Serializable