package com.e.cryptocracy.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.e.cryptocracy.R
import com.e.cryptocracy.databinding.ActivityMainBinding
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils
import com.e.cryptocracy.utils.Currency
import com.e.cryptocracy.utils.SortOrder
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth


class SplashScreen : AppCompatActivity() {
    private val TAG = "SplashScreen"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    override fun onStart() {
        super.onStart()
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                checkLogin()
            }
        }.start()
    }

    private fun checkLogin() {

        if (AppUtils.getCurrentUser() != null) {
            startActivity(Intent(this, HomeScreen::class.java))
            finish()
        } else startLogin()
    }

    private fun startLogin() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            AppConstant.RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AppConstant.RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                AppConstant.showToast("sign in Successfully !!")
                try {
                    AppUtils.updateUserInfo(user)
                    AppUtils.setValue(AppConstant.CURRENCY, Currency.USD, this)
                    AppUtils.setValue(AppConstant.SORT_ORDER, SortOrder.market_cap_asc, this)
                    startActivity(Intent(this, SelectCurrencyActivity::class.java))
                    finish()
                } catch (ex: Exception) {
                    Log.d(TAG, "onActivityResult: " + ex.localizedMessage)
                    startActivity(Intent(this, HomeScreen::class.java))
                    finish()
                }


            } else {
                if (response != null) {
                    Log.d(TAG, "onActivityResult: " + (response.error?.errorCode ?: "NO Data"))
                }
            }
        }
    }


}


