package com.khaledamin.mawsoaa.datasource.api

import com.khaledamin.mawsoaa.models.User
import com.khaledamin.mawsoaa.models.requests.FindTopicRequest
import com.khaledamin.mawsoaa.models.requests.GetAllCategoriesRequest
import com.khaledamin.mawsoaa.models.requests.GetTopicsByCategoryIdRequest
import com.khaledamin.mawsoaa.models.requests.LoginRequest
import com.khaledamin.mawsoaa.models.requests.SignupRequest
import com.khaledamin.mawsoaa.models.responses.GetAllCategoriesResponse
import com.khaledamin.mawsoaa.models.responses.GetTopicsResponse
import com.khaledamin.mawsoaa.models.responses.SignupResponse
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface MawredyApi {
@POST("getAllCategories")
fun getAllCategories(@Body request: GetAllCategoriesRequest): Single<GetAllCategoriesResponse>
@POST("getTopicsByCategoryId")
fun getTopicsByCategoryId(@Body request: GetTopicsByCategoryIdRequest):Single<GetTopicsResponse>
@POST("findTopic")
fun findTopic(@Body request: FindTopicRequest):Single<GetTopicsResponse>
@POST("login")
fun login(@Body request: LoginRequest):Single<User>
@POST("signup")
fun signup(@Body request: SignupRequest):Single<SignupResponse>
}
