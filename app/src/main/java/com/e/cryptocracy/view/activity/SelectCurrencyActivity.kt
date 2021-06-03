@file:Suppress("UNREACHABLE_CODE")

package com.e.cryptocracy.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.cryptocracy.R
import com.e.cryptocracy.adapter.CurrencyAdapter
import com.e.cryptocracy.databinding.ActivitySelectCurrencyBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.utils.*
import com.e.cryptocracy.utils.App.context

class SelectCurrencyActivity : AppCompatActivity(), AdapterInterface {
    private val TAG = "SelectCurrencyActivity"

    lateinit var binding: ActivitySelectCurrencyBinding
    lateinit var currencyAdapter: CurrencyAdapter
    private var apiUtils = APIUtils()
    var dummyData: List<CurrencyItem> = ArrayList()
    lateinit var mActivity: Activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_currency)
    }

    override fun onStart() {
        super.onStart()
        mActivity = this;
        title = getString(R.string.select_currency)


        getCurrencyData()

    }


    private fun getCurrencyData() {

        if (AppUtils.isNetworkConnected(App.context)) {
            AppUtils.showRequestDialog(this)
            apiUtils.getAllCurrency(object : ApiCallbackInterface {
                override fun onSuccess(obj: Any) {
                    AppUtils.hideDialog()
                    val currencyList: ArrayList<String> = obj as ArrayList<String>
                    for (a in 0 until currencyList.size) {
                        val currencyItem = CurrencyItem(currencyList[a])
                        dummyData += CurrencyItem(currencyList[a])
                        Log.d(TAG, "currency: ${currencyItem.currencyId}")
                    }
                    currencyAdapter = CurrencyAdapter(dummyData, this@SelectCurrencyActivity)
                    binding.recCurreency.addItemDecoration(
                        DividerItemDecoration(

                            context,
                            LinearLayoutManager.VERTICAL
                        )
                    )
                    binding.recCurreency.adapter = currencyAdapter

                }

                override fun onFailed(msg: String?) {
                    AppUtils.hideDialog()
                    Log.d(TAG, "onFailed: $msg")
                }

            })
        } else AppConstant.showToast(getString(R.string.no_internet))

    }

    override fun onItemClick(obj: Any) {
        val currency: String = obj as String
        AppUtils.setValue(
            AppConstant.CURRENCY,
            currency,
            this@SelectCurrencyActivity
        )
        AppConstant.showToast(getString(R.string.currency_changed))
        startActivity(Intent(this@SelectCurrencyActivity, HomeScreen::class.java))
        finish()
    }

}

