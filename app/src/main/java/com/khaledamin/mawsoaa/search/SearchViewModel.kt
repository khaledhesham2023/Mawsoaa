package com.khaledamin.mawsoaa.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khaledamin.mawsoaa.datasource.repo.Repo
import com.khaledamin.mawsoaa.models.requests.FindTopicRequest
import com.khaledamin.mawsoaa.models.responses.GetTopicsResponse
import com.khaledamin.mawsoaa.utils.ApiRequestManager
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private val _searchTopicLiveData = MutableLiveData<ViewState<GetTopicsResponse>>()
    var apiRequestManager = ApiRequestManager(context)
    var repo = Repo()

    val searchTopicLiveData: LiveData<ViewState<GetTopicsResponse>>
        get() = _searchTopicLiveData

    fun findTopic(request: FindTopicRequest) =  apiRequestManager.requestApi(repo.findTopic(request),_searchTopicLiveData)
//    {
//        MawsoaaApiService.retrofitService.findTopic(request)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : SingleObserver<GetTopicsResponse> {
//                override fun onSubscribe(d: Disposable) {
////                    TODO("Not yet implemented")
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.i("TAGGG", e.message!!)
//                }
//
//                override fun onSuccess(t: GetTopicsResponse) {
//                    _searchTopicLiveData.value = t
//                }
//
//            })
//    }
}