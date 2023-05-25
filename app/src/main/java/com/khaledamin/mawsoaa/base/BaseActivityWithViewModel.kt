package com.khaledamin.mawsoaa.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivityWithViewModel<VB:ViewDataBinding,VM:ViewModel> : BaseActivity<VB>(){

    protected lateinit var viewModel:VM

    abstract val viewModelClass:Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass]
        setupListeners()
        setupObservers()

    }

    abstract fun setupListeners()
    abstract fun setupObservers()

}
