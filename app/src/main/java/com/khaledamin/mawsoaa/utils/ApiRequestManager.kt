package com.khaledamin.mawsoaa.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ApiRequestManager(val context: Context) {

    fun <T> requestApi(request: Single<T>, liveData: MutableLiveData<ViewState<T>>) {
        if (isInternetConnected(context)) {
            liveData.value = ViewState.Loading
            request.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(object : SingleObserver<T>{
                override fun onSubscribe(d: Disposable) {
//                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable) {
                    liveData.value = ViewState.Error(e.message!!)
                    Log.i("ApiRequestManager" ,"${e.message}")
                }

                override fun onSuccess(t: T) {
                    liveData.value = ViewState.Success(t,"Success")
                    Log.i("ApiRequestManager" , "Success")

                }

            })
        }
    }
}