package com.oxygen.freecorona.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseViewModelFragment<T : ViewModel>(clazz: KClass<T>) : BaseFragment() {

    protected val fragmentViewModel: T by viewModel(clazz)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataBindingVariable()
    }

    private fun setDataBindingVariable() {
        viewDataBinding.setVariable(BR.viewModel, fragmentViewModel)
    }
}