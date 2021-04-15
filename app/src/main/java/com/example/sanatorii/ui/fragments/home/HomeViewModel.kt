package com.example.sanatorii.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sanatorii.model.Model
import com.example.sanatorii.repository.Repository

class HomeViewModel(private val repository: Repository): ViewModel(){

    fun getDB(): LiveData<MutableList<Model>>{
        val mutableData = MutableLiveData<MutableList<Model>>()
        repository.getData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }




}