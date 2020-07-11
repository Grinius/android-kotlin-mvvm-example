package com.oxygen.freecorona.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oxygen.freecorona.arcextensions.SingleLiveEvent

class SplashViewModel : ViewModel() {

    private val _navigateToInfo = SingleLiveEvent<Any>()

    val navigateToInfo: LiveData<Any>
        get() = _navigateToInfo

    fun onGetStartedClick() {
        _navigateToInfo.call()
    }
}