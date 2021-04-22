package com.example.sanatorii.firebaseAuth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.sanatorii.R
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    companion object{
       const val SIGN_IN_RESULT_CODE = 111
    }
    private val viewModel by viewModels<LoginVM>()
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    private fun launchSignIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build(), SIGN_IN_RESULT_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        sign_btn.setOnClickListener { launchSignIn() }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            navController.popBackStack(R.id.navigation_home, false)
        }
        viewModel.authenticationState.observe(viewLifecycleOwner, {it->
            when(it){
                LoginVM.AuthenticationState.AUTHENTICATED ->{
                    navController.popBackStack()
                    navController.navigate(R.id.navigation_profile)
                }
                LoginVM.AuthenticationState.INVALID_AUTHENTICATION->{
                    Toast.makeText(requireContext(),"Invalid Account",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
   }
}