package com.oxygen.freecorona.features.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.oxygen.freecorona.R
import com.oxygen.freecorona.base.BaseViewModelFragment

class SplashFragment : BaseViewModelFragment<SplashViewModel>(SplashViewModel::class) {

    override fun getLayoutResId(): Int = R.layout.screen_splash
    override fun getFragmentTag() = "startFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewLiveData()
    }

    private fun observeViewLiveData() {
//        fragmentViewModel.navigateToInfo.observe(viewLifecycleOwner, Observer { _ ->
//            findNavController().navigate(
//                StartFragmentDirections.actionStartFragmentToInfoFragment()
//            )
//        })
    }
}