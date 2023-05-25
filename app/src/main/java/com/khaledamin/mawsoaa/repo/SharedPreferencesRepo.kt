package com.khaledamin.mawsoaa.repo

import android.content.Context
import com.google.gson.Gson
import com.khaledamin.mawsoaa.models.User
import com.khaledamin.mawsoaa.utils.Constants

class SharedPreferencesRepo(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE)

    fun setLoggedIn(isLoggedIn: Boolean) =
        sharedPreferences.edit().putBoolean(Constants.IS_LOGGED_IN, isLoggedIn).apply()

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(Constants.IS_LOGGED_IN, false)

    fun saveBearerToken(uId: String?) =
        sharedPreferences.edit().putString(Constants.BEARER_TOKEN, uId).apply()

    fun getBearerToken(): String? = sharedPreferences.getString(Constants.BEARER_TOKEN, "")

    fun setCurrentLanguage(lang: String?) =
        sharedPreferences.edit().putString(Constants.CURRENT_LANGUAGE, lang).apply()

    fun getCurrentLang() = sharedPreferences.getString(Constants.CURRENT_LANGUAGE, "")

    fun saveUser(user: User) = sharedPreferences.edit().putString("USER",Gson().toJson(user)).apply()

    fun getUser(): User? = Gson().fromJson(sharedPreferences.getString("USER",""),User::class.java)
}