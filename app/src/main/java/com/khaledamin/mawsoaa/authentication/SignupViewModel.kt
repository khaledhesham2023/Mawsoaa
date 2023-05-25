package com.khaledamin.mawsoaa.authentication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khaledamin.mawsoaa.datasource.repo.Repo
import com.khaledamin.mawsoaa.models.requests.SignupRequest
import com.khaledamin.mawsoaa.models.responses.SignupResponse
import com.khaledamin.mawsoaa.utils.ApiRequestManager
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private var _signupLiveData = MutableLiveData<ViewState<SignupResponse>>()

    val signupLiveData:LiveData<ViewState<SignupResponse>>
        get() = _signupLiveData

    var apiRequestManager = ApiRequestManager(context)

    var repo = Repo()

    fun signUp(request: SignupRequest) =  apiRequestManager.requestApi(repo.signup(request),_signupLiveData)
}