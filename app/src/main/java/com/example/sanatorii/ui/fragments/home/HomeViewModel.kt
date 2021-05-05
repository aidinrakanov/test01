package com.example.sanatorii.ui.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sanatorii.model.Model
import com.example.sanatorii.repository.Repository
import com.example.sanatorii.repository.Status

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _state = MutableLiveData<MutableList<Model>>()
    val state: LiveData<MutableList<Model>> = _state

    private val isLoading = MutableLiveData(false)

    fun getData() {

        repository.getData().observeForever {

            if (it?.status == Status.SUCCESS) {
                _state.postValue(it.data!!)
            } else if (it?.status == Status.ERROR) {
                Log.e("TAG", "register:${it.message} ")
            }

            when (it?.status) {
                Status.LOADING -> isLoading.value = true
                else -> isLoading.value = false
            }
        }
    }
}