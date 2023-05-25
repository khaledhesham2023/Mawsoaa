package com.khaledamin.mawsoaa.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.khaledamin.mawsoaa.loading.LoadingDialog
import com.khaledamin.mawsoaa.repo.SharedPreferencesRepo

abstract class BaseActivity<VB: ViewDataBinding>:AppCompatActivity() {

    protected lateinit var viewBinding : VB

    abstract val layout:Int

    protected lateinit var sharedPreferencesRepo: SharedPreferencesRepo

    protected lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this,layout)
        sharedPreferencesRepo = SharedPreferencesRepo(this)
        loadingDialog = LoadingDialog(this)


    }
}