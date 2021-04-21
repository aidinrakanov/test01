package com.example.sanatorii.repository

import android.util.Log
import com.example.sanatorii.model.Model
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

//@ExperimentalCoroutinesApi
class Repository {

}

//val ggg = db.get().addOnSuccessListener {
//    for (document in it) {
//        Log.d("ololo", "${document.id} => ${document.data}")
//        val model = document.toObject(Model::class.java) }
//}





//private val db = FirebaseFirestore.getInstance().collection("kurort")
//
//fun observeData() = flow<State<List<Model>>>() {
//    emit(State.loading())
//
//    val getDB = db.get().await()
//    val posts = getDB.toObjects(Model::class.java)
//
//    emit(State.success(posts))
//
//}.catch {
//    emit(State.failed(it.message.toString()))
//}