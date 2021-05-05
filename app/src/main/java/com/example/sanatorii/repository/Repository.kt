package com.example.sanatorii.repository

import androidx.lifecycle.liveData
import com.example.sanatorii.model.Model
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await

class Repository {
    fun getData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val eventList = mutableListOf<Model>()
        val resultList = FirebaseFirestore.getInstance()
            .collection("kurort")
            .get().await()

        for (document in resultList) {
            val adress = document.getString("adress")
            val cost = document.getLong("cost")
            val fullInfo = document.getString("fullInfo")
            val location = document.getGeoPoint("geoPosition")
            val image = document.getString("image")
            val info = document.getString("info")
            val name = document.getString("name")
            val rating = document.getDouble("rating")
            val phoneNum = document.getString("telephone")
            val model = Model(
                adress, cost?.toInt(), fullInfo, location,
                image, info, name, rating?.toFloat(), phoneNum
            )
            if (model != null) eventList.add(model)
        }
        try {
            if (eventList[0].adress?.isNotEmpty()!!) {
                emit(Resource.success(eventList))
            }
        } catch (e: Exception) {
            emit(e.message?.let { Resource.error(null, it) })
        }
    }
}