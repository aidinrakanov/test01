package com.example.sanatorii.ui.fragments.posts

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanatorii.R
import com.example.sanatorii.model.PostsModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_upload.*

class UploadFragment : Fragment() {

    private var imageUri2: Uri? = null
    private val pickImage2 = 77
    lateinit var titleUpload : String
    lateinit var descriptionUpload : String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseImage()
        getTextFromEditText()
    }

    private fun getTextFromEditText() {

        titleUpload = post_add_title.text.toString()
        descriptionUpload = post_add_title.text.toString()

    }

    private fun chooseImage() {
        post_add_image.setOnClickListener {
            val sendImage = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult( sendImage,pickImage2)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == pickImage2 ){
            imageUri2 = data?.data
            post_add_image.setImageURI(imageUri2)
        }
    }
    var sendDB = FirebaseFirestore.getInstance().collection("lenta")

}