package com.e.cryptocracy.utils

import android.app.Activity
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.CoinModel
import com.e.cryptocracy.model.MarketModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class APIUtils {
    private val TAG = "APIUtils"
    fun getAllCurrency(apiCallbackInterface: ApiCallbackInterface) {
        try {
            val api = URLUtils.getAPIService()
            val specialityResCall = api.allCurrency
            specialityResCall.enqueue(object : Callback<ArrayList<String>?> {
                override fun onResponse(
                    call: Call<ArrayList<String>?>, response: Response<ArrayList<String>?>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<ArrayList<String>?>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun getMarketData(
        id: String?,
        page: Int?,
        activity: Activity?,
        apiCallbackInterface: ApiCallbackInterface,
    ) {
        try {
            val price_change_percentage = "1h,24h,7d"
            val per_page = 100
            val order: String = AppUtils.getValue(AppConstant.SORT_ORDER, activity)
            val vs_currency: String = AppUtils.getValue(AppConstant.CURRENCY, activity)
            var category: String = AppUtils.getValue(AppConstant.CATEGORY, activity)

            val api = URLUtils.getAPIService()
            val specialityResCall = api.coinMarketData(
                vs_currency,
                order,
                per_page,
                page,
                price_change_percentage
            )
            specialityResCall.enqueue(object : Callback<List<MarketModel>> {
                override fun onResponse(
                    call: Call<List<MarketModel>>, response: Response<List<MarketModel>>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                        Log.d(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(call: Call<List<MarketModel>>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                    Log.d(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCoinDataById(
        id: String,
        apiCallbackInterface: ApiCallbackInterface,
    ) {
        try {
            val api = URLUtils.getAPIService()
            val specialityResCall = api.getCoinDataById(id, "true", true, true, true, true, true)
            specialityResCall.enqueue(object : Callback<CoinModel> {
                override fun onResponse(
                    call: Call<CoinModel>, response: Response<CoinModel>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                        Log.d(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(call: Call<CoinModel>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                    Log.d(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

