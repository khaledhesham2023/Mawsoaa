package com.khaledamin.mawsoaa.topics

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khaledamin.mawsoaa.datasource.repo.Repo
import com.khaledamin.mawsoaa.models.requests.GetTopicsByCategoryIdRequest
import com.khaledamin.mawsoaa.models.responses.GetTopicsResponse
import com.khaledamin.mawsoaa.utils.ApiRequestManager
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(@ApplicationContext context: Context): ViewModel() {

    private var _topicsByCategoryIdLiveData = MutableLiveData<ViewState<GetTopicsResponse>>()

    val topicsByCategoryIdLiveData: LiveData<ViewState<GetTopicsResponse>>
        get() = _topicsByCategoryIdLiveData

    var apiRequestManager = ApiRequestManager(context)
    var repo = Repo()

    fun getCategoryTopics(request: GetTopicsByCategoryIdRequest) = apiRequestManager.requestApi(repo.getTopicsByCategoryId(request),_topicsByCategoryIdLiveData)

}
