package com.oxygen.freecorona.module

import com.oxygen.freecorona.features.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
//    viewModel { InfoViewModel() }
//    viewModel { ConfirmationViewModel() }
//    viewModel { ReportViewModel(get(), get(), get(), get()) }
}