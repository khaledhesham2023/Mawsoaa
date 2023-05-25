package com.khaledamin.mawsoaa.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.base.BaseActivityWithViewModel
import com.khaledamin.mawsoaa.base.BaseFragmentWithViewModel
import com.khaledamin.mawsoaa.databinding.ActivitySignupBinding
import com.khaledamin.mawsoaa.models.requests.SignupRequest
import com.khaledamin.mawsoaa.utils.DisplayManager.Companion.removeErrorWhenEditing
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : BaseActivityWithViewModel<ActivitySignupBinding, SignupViewModel>() {
    override val viewModelClass: Class<SignupViewModel>
        get() = SignupViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeErrorWhenEditing(
            viewBinding.emailLayout,
            viewBinding.usernameLayout,
            viewBinding.passwordLayout,
            viewBinding.firstnameLayout,
            viewBinding.lastnameLayout,
            viewBinding.phoneLayout,
            viewBinding.confirmPasswordLayout
        )
    }
    override fun setupListeners() {
        viewBinding.loginText.setOnClickListener {
            finish()
        }
        viewBinding.createNewButton.setOnClickListener {
            if (isDataOk()) {
                viewModel.signUp(
                    SignupRequest(
                        viewBinding.email.text.toString().trim(),
                        viewBinding.username.text.toString().trim(),
                        viewBinding.password.text.toString().trim(),
                        viewBinding.firstname.text.toString().trim(),
                        viewBinding.lastname.text.toString().trim(),
                        viewBinding.phone.text.toString().trim()
                    ),
                )
            }
        }
    }

    override fun setupObservers() {
        viewModel.signupLiveData.observe(this, Observer {
            when(it){
                is ViewState.Loading -> {
                    loadingDialog.show()
                }
                is ViewState.Success -> {
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                    loadingDialog.dismiss()
                    finish()
                }
                is ViewState.Error -> {
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun isDataOk(): Boolean {
        var isDataOk = true
        if (TextUtils.isEmpty(viewBinding.email.text.toString())) {
            viewBinding.emailLayout.error = getString(R.string.e_mail_required_error)
            isDataOk = false
        }
        if (TextUtils.isEmpty(viewBinding.username.text.toString().trim())) {
            viewBinding.usernameLayout.error = getString(R.string.username_required_error)
            isDataOk = false
        }
        if (TextUtils.isEmpty(viewBinding.password.text.toString().trim())){
            viewBinding.passwordLayout.error = getString(R.string.password_required_error)
            isDataOk = false
        }
        if (TextUtils.isEmpty(viewBinding.confirmPassword.text.toString().trim())){
            viewBinding.confirmPasswordLayout.error = getString(R.string.confirm_password_required_error)
            isDataOk = false
        }
        if (TextUtils.isEmpty(viewBinding.firstname.text.toString().trim())){
            viewBinding.firstnameLayout.error = getString(R.string.firstname_required_error)
            isDataOk = false
        }
        if (TextUtils.isEmpty(viewBinding.lastname.text.toString().trim())){
            viewBinding.lastnameLayout.error = getString(R.string.lastname_required_error)
            isDataOk = false
        }
        if (TextUtils.isEmpty(viewBinding.phone.text.toString().trim())){
            viewBinding.phoneLayout.error = getString(R.string.phone_required_error)
            isDataOk = false
        }
        if (viewBinding.phone.text.toString().length != 11){
            viewBinding.phone.error = getString(R.string.phone_length_error)
            isDataOk = false
        }
        if (viewBinding.password.text.toString() != viewBinding.confirmPassword.text.toString()){
            viewBinding.passwordLayout.error = getString(R.string.not_matching)
            viewBinding.confirmPassword.error = getString(R.string.not_matching)
            isDataOk = false
        }
        return isDataOk
    }

    override val layout: Int
        get() = R.layout.activity_signup

}