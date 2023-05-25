package com.khaledamin.mawsoaa

import com.khaledamin.mawsoaa.base.BaseFragment
import com.khaledamin.mawsoaa.databinding.FragmentAboutBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override val layout: Int
        get() = R.layout.fragment_about

}