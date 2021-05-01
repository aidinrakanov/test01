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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sanatorii.R
import com.example.sanatorii.model.PostsModel
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_upload.*


class UploadFragment : Fragment() {

    private var imageUri2: Uri? = null
    private val pickImage2 = 77
    lateinit var titleUpload: String
    lateinit var descriptionUpload: String
    lateinit var storage: StorageReference
    lateinit var postUri: String
    lateinit var postModel: PostsModel
    val viewModel: UploadViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseImage()
        viewModelObs()
        onSuccessListener()
    }

    private fun onSuccessListener() {
        viewModel.success.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "success", Toast.LENGTH_LONG).show();
                findNavController().navigateUp()
            }
        }
    }

    private fun viewModelObs() {
        btn_upload.setOnClickListener {
            titleUpload = post_add_title.text.toString()
            descriptionUpload = post_add_descr.text.toString()
            viewModel.addPost(titleUpload, descriptionUpload, imageUri2.toString())
        }
    }

    private fun chooseImage() {
        post_add_image.setOnClickListener {
            val sendImage = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(sendImage, pickImage2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == pickImage2) {

            Glide.with(this).load(data?.data).into(post_add_image)
            imageUri2 = data?.data
            post_add_image.setImageURI(imageUri2)
        }
    }
}