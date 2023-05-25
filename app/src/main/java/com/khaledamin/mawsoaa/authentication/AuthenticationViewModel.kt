package com.khaledamin.mawsoaa.authentication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khaledamin.mawsoaa.datasource.repo.Repo
import com.khaledamin.mawsoaa.models.User
import com.khaledamin.mawsoaa.models.requests.LoginRequest
import com.khaledamin.mawsoaa.utils.ApiRequestManager
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private var _loginLiveData = MutableLiveData<ViewState<User>>()
    var apiRequestManager = ApiRequestManager(context)
    var repo = Repo()

    val loginLiveData: LiveData<ViewState<User>>
        get() = _loginLiveData

    fun login(request: LoginRequest) = apiRequestManager.requestApi(repo.login(request),_loginLiveData)

}