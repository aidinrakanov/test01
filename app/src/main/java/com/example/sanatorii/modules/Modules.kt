package com.example.sanatorii.modules

import com.example.sanatorii.repository.Repository
import com.example.sanatorii.ui.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var appModule = module {

    viewModel {
        HomeViewModel(get())
    }
}

val repositoriesModule = module {
    factory { Repository() }
}
