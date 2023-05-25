package com.khaledamin.mawsoaa.admin

import android.os.Bundle
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.base.BaseFragmentWithViewModel
import com.khaledamin.mawsoaa.databinding.FragmentAdminBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminFragment : BaseFragmentWithViewModel<FragmentAdminBinding,AdminViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListeners()
    }
    override val viewModelClass: Class<AdminViewModel>
        get() = AdminViewModel::class.java

    override fun setupObservers() {
//        TODO("Not yet implemented")
    }

    override val layout: Int
        get() = R.layout.fragment_admin

    private fun setupListeners(){

    }


}

