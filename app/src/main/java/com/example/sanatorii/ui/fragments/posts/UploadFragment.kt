package com.example.sanatorii.ui.fragments.posts

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sanatorii.R
import com.example.sanatorii.model.PostsModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_upload.*


class UploadFragment : Fragment() {

    private var imageUri2: Uri? = null
    private val pickImage2 = 77
    lateinit var titleUpload : String
    lateinit var descriptionUpload : String
    lateinit var storage : StorageReference
    lateinit var postUri: String
    lateinit var postModel: PostsModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseImage()
//        savePost()
        getTextFromEditText()
    }

//    private fun uploadImage(data: Uri) {
//        val reference = FirebaseStorage.getInstance()
//            .reference.child("posts" + ".jpg")
//        val uploadTask = reference.putFile(data)
//        uploadTask.continueWithTask { reference.downloadUrl }
//            .addOnCompleteListener { it ->
//                if (it.isSuccessful) {
//                    val downloadUrl =it.result
//                    postUri = downloadUrl.toString()
//                } else {
//                    Toast.makeText(
//                        context, "Ошибка",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//
//    }

//    private fun savePost() {
//        FirebaseFirestore.getInstance()
//            .collection("lenta")
//            .document("posts")
//            .set(postModel)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(
//                        context, "Успешно",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else {
//                    Toast.makeText(
//                        context, "Ошибка",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//    }

    private fun getTextFromEditText() {
        titleUpload = post_add_title.text.toString()
        descriptionUpload = post_add_title.text.toString()
//        var postsModel = PostsModel(postUri, titleUpload, descriptionUpload)

    }

    private fun chooseImage() {
        post_add_image.setOnClickListener {
            val sendImage = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(sendImage, pickImage2)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == pickImage2 ){
//            Glide.with(this).load(data?.data).into(post_add_image)
//            imageUri2 = data.data
//           post_add_image.setImageURI(imageUri2)
//            data?.data?.let { uploadImage(it) }

        }
    }




}