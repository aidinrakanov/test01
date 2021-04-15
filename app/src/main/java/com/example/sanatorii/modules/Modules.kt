package com.example.sanatorii.modules

import com.example.sanatorii.ui.fragments.home.HomeFragment
import com.example.sanatorii.ui.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


    var appModule : Module = module{

        viewModel { HomeViewModel(get()) }
    }
