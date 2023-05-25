package com.khaledamin.mawsoaa.authentication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.base.BaseActivity
import com.khaledamin.mawsoaa.base.BaseActivityWithViewModel
import com.khaledamin.mawsoaa.databinding.ActivityAuthenticationBinding
import com.khaledamin.mawsoaa.main.MainActivity
import com.khaledamin.mawsoaa.models.requests.LoginRequest
import com.khaledamin.mawsoaa.utils.Constants
import com.khaledamin.mawsoaa.utils.DisplayManager.Companion.removeErrorWhenEditing
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity :
    BaseActivityWithViewModel<ActivityAuthenticationBinding, AuthenticationViewModel>() {
    override val layout: Int
        get() = R.layout.activity_authentication
    override val viewModelClass: Class<AuthenticationViewModel>
        get() = AuthenticationViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeErrorWhenEditing(viewBinding.emailLayout,viewBinding.passwordLayout)
        setupListeners()
    }

    override fun setupListeners() {
        viewBinding.login.setOnClickListener {
            if (isDataOk()) {
                loadingDialog.show()
                viewModel.login(
                    LoginRequest(
                        viewBinding.email.text.toString().trim(),
                        viewBinding.password.text.toString().trim()
                    )
                )
            }
        }
        viewBinding.guest.setOnClickListener {
            startActivity(Intent(this@AuthenticationActivity,MainActivity::class.java))
            finish()
        }
        viewBinding.createNew.setOnClickListener {
            startActivity(Intent(this@AuthenticationActivity,SignupActivity::class.java))
        }
    }

    override fun setupObservers() {
        viewModel.loginLiveData.observe(this, Observer {
            when(it){
                is ViewState.Loading -> {
                    loadingDialog.show()
                }
                is ViewState.Success -> {
                    sharedPreferencesRepo.saveBearerToken(it.data.userToken)
                    sharedPreferencesRepo.setLoggedIn(true)
                    sharedPreferencesRepo.saveUser(it.data)
                    Toast.makeText(this, "Logged in successfully as ${it.data.username}", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@AuthenticationActivity, MainActivity::class.java))
                    finish()
                    loadingDialog.dismiss()
                }
                is ViewState.Error -> {
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                }
            }
        })
    }
    private fun isDataOk():Boolean{
        var isDataOk = true
        if (TextUtils.isEmpty(viewBinding.email.text.toString().trim())){
            viewBinding.emailLayout.error = getString(R.string.e_mail_required_error)
            isDataOk = false
        }
        if (TextUtils.isEmpty(viewBinding.password.text.toString().trim())){
            viewBinding.passwordLayout.error = getString(R.string.password_required_error)
            isDataOk = false
        }
        return isDataOk
    }
}

