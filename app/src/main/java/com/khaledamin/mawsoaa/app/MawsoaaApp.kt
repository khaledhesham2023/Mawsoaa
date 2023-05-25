package com.khaledamin.mawsoaa.app

import android.app.Application
import com.khaledamin.mawsoaa.utils.Constants
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@HiltAndroidApp
class MawsoaaApp:Application() {

}