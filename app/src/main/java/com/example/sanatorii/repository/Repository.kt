package com.example.sanatorii.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sanatorii.model.Model
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    private val db = FirebaseFirestore.getInstance().collection("kurort")

    fun getData(): LiveData<MutableList<Model>> {
        val mutableData = MutableLiveData<MutableList<Model>>()
        db.get().addOnSuccessListener {
            val listData = mutableListOf<Model>()

            for (document in it) {
                val image = document.getString("Image")
                val adress = document.getString("adress")
                val cost = document.get("cost")
                val fullinfo = document.getString("fullInfo")
                val position = document.getGeoPoint("geoPosition")
                val info = document.getString("info")
                val getname = document.getString("name")
                val rating = document.get("rating")
                val telephon = document.getString("telephone")
                val model = Model(
                    image!!, adress!!,
                    cost!! as Int, fullinfo!!, position!!, info!!, getname!!,
                    rating!! as Float, telephon!!)
                listData.add(model)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}