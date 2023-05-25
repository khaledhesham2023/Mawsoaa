package com.khaledamin.mawsoaa.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.khaledamin.mawsoaa.NavigationDirections
import com.khaledamin.mawsoaa.R
import com.khaledamin.mawsoaa.authentication.AuthenticationActivity
import com.khaledamin.mawsoaa.base.BaseActivity
import com.khaledamin.mawsoaa.databinding.ActivityMainBinding
import com.khaledamin.mawsoaa.utils.DisplayManager
import com.khaledamin.mawsoaa.utils.DisplayManager.Companion.setLocale
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layout: Int
        get() = R.layout.activity_main

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
//    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureTheUser()
//        mediaPlayer = MediaPlayer.create(this, R.raw.categories)

        Log.i("Language", "Language is : ${sharedPreferencesRepo.getCurrentLang()}")

        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).findNavController()

        appBarConfiguration = AppBarConfiguration.Builder(R.id.categoryFragment).build()

        setupActionBarWithNavController(navController, appBarConfiguration)

        viewBinding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.topicsFragment || destination.id == R.id.topicDetailsFragment) {
                viewBinding.bottomNavView.visibility = View.GONE
                //                    startMusic()
            } else {
                viewBinding.bottomNavView.visibility = View.VISIBLE
                //                    stopMusic()
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                logout()
            }

            R.id.changeLang -> {
                changeLanguage(sharedPreferencesRepo.getCurrentLang()!!)
            }

            R.id.search -> {
                findNavController(R.id.nav_host_fragment).navigate(NavigationDirections.actionGlobalSearchFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
                DisplayManager.showConfirmationDialog(
                    this,
                    R.string.confirm_logout,
                    R.string.logout,
                    R.string.cancel
                ) { _, _ ->
                    sharedPreferencesRepo.setLoggedIn(false)
                    sharedPreferencesRepo.saveBearerToken(null)
                    startActivity(Intent(this@MainActivity, AuthenticationActivity::class.java))
                    finish()
                }
    }

    private fun changeLanguage(lang: String) {
        DisplayManager.showConfirmationDialog(
            this,
            R.string.confirm_language,
            R.string.confirm,
            R.string.cancel
        ) { _, _ ->
            when (lang) {
                "en" -> {
                    sharedPreferencesRepo.setCurrentLanguage("ar")
                    setLocale(this, "ar")
                    refreshActivity()
                }

                "ar" -> {
                    sharedPreferencesRepo.setCurrentLanguage("en")
                    setLocale(this, "en")
                    refreshActivity()

                }
            }
        }
    }

    private fun refreshActivity() {
        startActivity(Intent(this@MainActivity, MainActivity::class.java))
        finish()
    }


    private fun configureTheUser() {
            viewBinding.bottomNavView.menu.findItem(R.id.adminFragment).isVisible =
                sharedPreferencesRepo.getUser()!!.userRole!!.contains("ADMIN") || sharedPreferencesRepo.getUser()!!.userRole!!.contains("SUPERVISOR")
    }
}

//    private fun stopMusic() {
//        mediaPlayer!!.stop()
//        mediaPlayer = null
//    }


//    override fun onResume() {
//        super.onResume()
//        startMusic()
//    }

//    override fun onStop() {
//        super.onStop()
//        stopMusic()
//    }



