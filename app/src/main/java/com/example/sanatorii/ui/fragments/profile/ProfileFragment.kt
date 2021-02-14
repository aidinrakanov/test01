package com.example.sanatorii.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sanatorii.firebaseAuth.LoginVM
import com.example.sanatorii.R
import com.example.sanatorii.ui.main.MainActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private val viewModel by viewModels<LoginVM>()

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
        prof_log_out.setOnClickListener { AuthUI.getInstance()
            .signOut(requireContext())
                findNavController().popBackStack()
            val intent = Intent (requireContext(), MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun observeAuthenticationState(){
        viewModel.authenticationState.observe(viewLifecycleOwner, {it->
            if (it == LoginVM.AuthenticationState.AUTHENTICATED){
                prof_name.text = FirebaseAuth.getInstance().currentUser?.displayName
                prof_email.text = FirebaseAuth.getInstance().currentUser?.email
                prof_number.text = FirebaseAuth.getInstance().currentUser?.phoneNumber
            }
        })
    }
}
//activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
//object : OnBackPressedCallback(true) {
//    override fun handleOnBackPressed() {
//
//    }
//})