package com.adryanev.presensi.di

import com.adryanev.presensi.MainActivityViewModel
import com.adryanev.presensi.ui.checkin.CheckinViewModel
import com.adryanev.presensi.ui.checkout.CheckoutViewModel
import com.adryanev.presensi.ui.home.HomeViewModel
import com.adryanev.presensi.ui.login.LoginViewModel
import com.adryanev.presensi.ui.tunjangan.TunjanganViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainActivityViewModel() }
    viewModel { HomeViewModel() }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { CheckinViewModel() }
    viewModel { CheckoutViewModel() }
    viewModel { TunjanganViewModel() }
}