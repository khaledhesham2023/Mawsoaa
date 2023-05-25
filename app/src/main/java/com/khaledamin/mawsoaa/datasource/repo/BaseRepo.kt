package com.khaledamin.mawsoaa.datasource.repo

import com.khaledamin.mawsoaa.datasource.api.MawredyApi
import com.khaledamin.mawsoaa.utils.Constants
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRepo<T> {

    var retrofit: Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
        GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()

    abstract val apiClassInterface : Class<T>
    val api = retrofit.create(MawredyApi::class.java)
}