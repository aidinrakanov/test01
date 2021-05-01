package com.example.sanatorii.ui.fragments.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sanatorii.App
import com.example.sanatorii.model.PostsModel
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    val postLiveData = MutableLiveData<ArrayList<PostsModel>>()

    fun getPostList() {
        viewModelScope.launch {
            App.db.collection("lenta").addSnapshotListener { value, _ ->
                val list = ArrayList<PostsModel>()
                if (list.size > 0 && list != null) {
                    list.clear()
                }
                for (index in value?.documents?.withIndex()!!) {
                    val doc = value.documents[index.index]
                    val post = PostsModel(
                        doc.get("title").toString(),
                        doc.get("desc").toString(),
                        doc.get("image").toString(),
                    )
                    list.add(post)
                }
                postLiveData.value = list
            }
        }

    }
}