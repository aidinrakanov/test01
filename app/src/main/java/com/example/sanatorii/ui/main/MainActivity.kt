package com.example.sanatorii.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sanatorii.R
import com.example.sanatorii.ui.fragments.InfoFragment
import com.example.sanatorii.ui.fragments.favorites.FavoriteFragment
import com.example.sanatorii.ui.fragments.home.HomeFragment
import com.example.sanatorii.ui.fragments.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        
        setupActionBarWithNavController(navController)
        navView.setupWithNavController(navController)
    }

}