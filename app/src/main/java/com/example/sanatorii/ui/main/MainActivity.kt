package com.example.sanatorii.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sanatorii.R
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
        val navController = navHostFragment.navController

        
        setupActionBarWithNavController(navController)
        navView.setupWithNavController(navController)
    }

}

//val home = HomeFragment()
//val favorite = FavoriteFragment()
//val profile = ProfileFragment()
//makeCurrentFragment(home)
//nav_view.setOnNavigationItemSelectedListener {
//    when(it.itemId){
//        R.id.navigation_home-> makeCurrentFragment(home)
//        R.id.navigation_favorites -> makeCurrentFragment(favorite)
//        R.id.navigation_profile -> makeCurrentFragment(profile)
//    }
//    true }
//
//private fun makeCurrentFragment(fragment: Fragment) =
//    supportFragmentManager.beginTransaction().apply {
//        replace(R.id.nav_host_fragment, fragment)
//        commit()
//    }