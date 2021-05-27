package com.e.cryptocracy.repository

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.MarketModel
import com.e.cryptocracy.utils.APIUtils

class Repo {
    private val TAG = "Repo"

    var marketLiveData: MutableLiveData<List<MarketModel>>? = null
    fun getCoinData(
        id: String?,
        page: Int?,
        activity: Activity?
    ): MutableLiveData<List<MarketModel>>? {

        if (marketLiveData == null) {
            marketLiveData = MutableLiveData<List<MarketModel>>()
        }


        loadCoinData(id, page, activity)
        return marketLiveData
    }

    private fun loadCoinData(id: String?, page: Int?, activity: Activity?) {


        val apiUtils = APIUtils()
        apiUtils.getMarketData(id, page, activity, object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                var coins: List<MarketModel> = obj as List<MarketModel>
                Log.d(TAG, "onSuccess: $coins")
                marketLiveData?.value = coins
            }

            override fun onFailed(msg: String) {

            }

        })
    }
}