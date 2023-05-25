package com.khaledamin.mawsoaa.datasource.repo

import com.khaledamin.mawsoaa.datasource.api.MawredyApi
import com.khaledamin.mawsoaa.models.requests.FindTopicRequest
import com.khaledamin.mawsoaa.models.requests.GetAllCategoriesRequest
import com.khaledamin.mawsoaa.models.requests.GetTopicsByCategoryIdRequest
import com.khaledamin.mawsoaa.models.requests.LoginRequest
import com.khaledamin.mawsoaa.models.requests.SignupRequest

class Repo : BaseRepo<MawredyApi>() {

    override val apiClassInterface: Class<MawredyApi>
        get() = MawredyApi::class.java


    fun getAllCategories(request: GetAllCategoriesRequest) = api.getAllCategories(request)
    fun getTopicsByCategoryId(request: GetTopicsByCategoryIdRequest) = api.getTopicsByCategoryId(request)
    fun findTopic(request: FindTopicRequest) = api.findTopic(request)
    fun login(request: LoginRequest) = api.login(request)
    fun signup(request: SignupRequest) = api.signup(request)

}