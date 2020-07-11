package com.oxygen.freecorona.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected lateinit var viewDataBinding: ViewDataBinding
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when {
            view != null -> view
            else -> {
                viewDataBinding =
                    DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
                viewDataBinding.lifecycleOwner = viewLifecycleOwner
                viewDataBinding.executePendingBindings()
                viewDataBinding.root
            }
        }
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun getFragmentTag(): String
}