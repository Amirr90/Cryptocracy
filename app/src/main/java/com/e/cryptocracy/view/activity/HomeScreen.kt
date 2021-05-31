package com.e.cryptocracy.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.e.cryptocracy.R
import com.e.cryptocracy.databinding.ActivityHomeScreenBinding
import com.e.cryptocracy.utils.AppUtils
import com.firebase.ui.auth.AuthUI
import java.util.*

class HomeScreen : AppCompatActivity() {

    lateinit var binding: ActivityHomeScreenBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen)

        //setting Nav Controller
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        Objects.requireNonNull(supportActionBar)!!.hide()
    }

    override fun onStart() {
        super.onStart()
        updateToken();

    }

    override fun onSupportNavigateUp(): Boolean {
        try {
            AppUtils.hideSoftKeyboard(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return navController.navigateUp()
    }

    private fun logout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashScreen::class.java))
                finish()
            }

    }

    private fun updateToken() {

        AppUtils.updateToken()
    }
}