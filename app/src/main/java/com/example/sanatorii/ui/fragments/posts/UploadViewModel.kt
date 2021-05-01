package com.example.sanatorii.ui.fragments.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sanatorii.App

class UploadViewModel : ViewModel() {

    var success = MutableLiveData<Boolean>()

    fun addPost(
        title: String,
        desc: String,
        imageURL: String
    ) {
        val task: HashMap<String, Any> = HashMap()
        task["title"] = title
        task["desc"] = desc
        task["image"] = imageURL

        App.db.collection("lenta").add(task).addOnCompleteListener {
            if (it.isSuccessful) {
                success.value = true
            }
        }
    }

}