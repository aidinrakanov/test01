package com.example.sanatorii.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sanatorii.model.Model
import com.example.sanatorii.repository.Repository

class HomeViewModel(private val repository: Repository): ViewModel(){

    var getDB = MutableLiveData<MutableList<Model>>()

    fun fetchData(){
//        getDB.value = repository.getData()
    }


}