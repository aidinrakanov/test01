package com.example.sanatorii.ui.fragments.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sanatorii.firebaseAuth.LoginVM
import com.example.sanatorii.R
import com.example.sanatorii.ui.main.MainActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private val viewModel by viewModels<LoginVM>()
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthenticationState()
        changeImage1()
        changeImage2()

    }

    private fun observeAuthenticationState() {
        viewModel.authenticationState.observe(viewLifecycleOwner,{
            when (it){
                LoginVM.AuthenticationState.UNAUTHENTICATED ->{
                    prof_log_in.visibility = View.VISIBLE
                    prof_log_out.visibility = View.GONE
                    prof_log_in.setOnClickListener {
                        findNavController().navigate(R.id.navigation_home) }
                }
                LoginVM.AuthenticationState.AUTHENTICATED ->{
                    prof_log_in.visibility = View.GONE
                    prof_log_out.visibility = View.VISIBLE
                    prof_name.text = FirebaseAuth.getInstance().currentUser?.displayName
                    prof_email.text = FirebaseAuth.getInstance().currentUser?.email
                    prof_number.text = FirebaseAuth.getInstance().currentUser?.phoneNumber
                    prof_log_out.setOnClickListener {
                        findNavController().navigate(R.id.loginFragment)
                        AuthUI.getInstance().signOut(requireContext())
                         }
                }
            }
        })
    }

    private fun changeImage2() {
        prof_circle_image.setOnClickListener {
            val circleImage = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(circleImage, pickImage)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage ){
            imageUri = data?.data
            prof_circle_image.setImageURI(imageUri)
        }
    }

    private fun changeImage1() {
       prof_top_image.setOnClickListener {

       }
    }


}
