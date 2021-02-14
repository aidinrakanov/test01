package com.example.sanatorii.firebaseAuth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class LoginVM : ViewModel() {
    enum class AuthenticationState{
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null){
            AuthenticationState.AUTHENTICATED
        }else{
            AuthenticationState.UNAUTHENTICATED
        }
    }
}