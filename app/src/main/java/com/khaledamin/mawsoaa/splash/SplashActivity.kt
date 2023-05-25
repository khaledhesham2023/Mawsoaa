package com.khaledamin.mawsoaa.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.base.BaseActivity
import com.khaledamin.mawsoaa.authentication.AuthenticationActivity
import com.khaledamin.mawsoaa.databinding.ActivitySplashBinding
import com.khaledamin.mawsoaa.main.MainActivity
import com.khaledamin.mawsoaa.utils.DisplayManager.Companion.setLocale

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val layout: Int
        get() = R.layout.activity_splash

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(sharedPreferencesRepo.getCurrentLang() == ""){
            setLocale(this,"en")
            sharedPreferencesRepo.setCurrentLanguage("en")
        } else {
            setLocale(this,sharedPreferencesRepo.getCurrentLang()!!)
        }

        Handler.createAsync(Looper.getMainLooper()).postDelayed(Runnable {
            Log.i("TAGG", sharedPreferencesRepo.isLoggedIn().toString())
            if (sharedPreferencesRepo.isLoggedIn()) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, AuthenticationActivity::class.java))
                finish()
            }
        }, 3000L)


        viewBinding.lifecycleOwner = this

    }

}