package com.example.sanatorii.ui.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sanatorii.App
import kotlinx.coroutines.launch

class InfoViewModel : ViewModel() {
    val isSuccessfully = MutableLiveData<Boolean>()

    fun postRating(id: Int, text: String, rating: Double) {
        viewModelScope.launch {
            val washingtonRef = App.db.collection("kurort").document(id.toString())
            val data: MutableMap<String, Any> = HashMap()
            data["rating"] = rating
            data["comment"] = text

            washingtonRef.update(data)
                .addOnSuccessListener {
                    isSuccessfully.value = true
                    Log.d("TAG", "DocumentSnapshot successfully updated!")
                }
                .addOnFailureListener { e ->
                    isSuccessfully.value = false
                    Log.w("TAG", "Error updating document", e)
                }
        }
    }
}