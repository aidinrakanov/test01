package com.example.sanatorii.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sanatorii.model.Model
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    private val db = FirebaseFirestore.getInstance().collection("kurort")

     fun getData():LiveData<MutableList<Model>> {
         val mutableData = MutableLiveData<MutableList<Model>>()
         db.get().addOnSuccessListener {
             val listData = mutableListOf<Model>()
             for (document in it ){
             val image_home = document.getString("")
         } }
     }
    }