package com.khaledamin.mawsoaa.category

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khaledamin.mawsoaa.datasource.repo.Repo
import com.khaledamin.mawsoaa.models.requests.GetAllCategoriesRequest
import com.khaledamin.mawsoaa.models.responses.GetAllCategoriesResponse
import com.khaledamin.mawsoaa.utils.ApiRequestManager
import com.khaledamin.mawsoaa.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(@ApplicationContext context: Context) :
    ViewModel() {

    private var _categoriesLiveData = MutableLiveData<ViewState<GetAllCategoriesResponse>>()

    val categoriesLiveData:LiveData<ViewState<GetAllCategoriesResponse>>
        get() = _categoriesLiveData

    private var apiRequestManager = ApiRequestManager(context)

    var repo = Repo()


    fun getMainCategories(request: GetAllCategoriesRequest) = apiRequestManager.requestApi(repo.getAllCategories(request),_categoriesLiveData)

}