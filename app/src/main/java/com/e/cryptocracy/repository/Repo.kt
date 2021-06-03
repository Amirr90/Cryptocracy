package com.e.cryptocracy.repository

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.MarketModel
import com.e.cryptocracy.model.responseModel.TrendingCoinResponse
import com.e.cryptocracy.utils.APIUtils
import com.e.cryptocracy.utils.AppConstant
import javax.inject.Inject

class Repo @Inject constructor(private val apiUtils: APIUtils) {
    private val TAG = "Repo"
    var marketLiveData: MutableLiveData<List<MarketModel>>? = null
    var marketLiveFavData: MutableLiveData<List<MarketModel>>? = null
    var marketTrendingCoinsLiveData: MutableLiveData<TrendingCoinResponse>? = null
    fun getCoinData(
        id: String?,
        page: Int?,
    ): MutableLiveData<List<MarketModel>>? {
        marketLiveData = MutableLiveData<List<MarketModel>>()
        loadCoinData(id, page)
        return marketLiveData
    }

     fun loadCoinData(id: String?, page: Int?) {
        apiUtils.getMarketData(id, page, object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                val coins: List<MarketModel> = obj as List<MarketModel>
                Log.d(TAG, "onSuccess: $coins")
                marketLiveData?.value = coins
            }

            override fun onFailed(msg: String?) {

            }

        })
    }

    fun getTrendingCoinData(): MutableLiveData<TrendingCoinResponse>? {
        if (marketTrendingCoinsLiveData == null) {
            marketTrendingCoinsLiveData = MutableLiveData<TrendingCoinResponse>()
        }
        loadTendingCoinData()
        return marketTrendingCoinsLiveData
    }

    private fun loadTendingCoinData() {
        apiUtils.loadTendingCoinData(object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                val coins: TrendingCoinResponse = obj as TrendingCoinResponse
                Log.d(TAG, "onSuccess: $coins")
                marketTrendingCoinsLiveData?.value = coins
            }

            override fun onFailed(msg: String?) {
                Log.d(TAG, "onFailed: $msg")
                AppConstant.showToast("failed to load trending data !!")
            }

        })
    }

    fun getFavCoinData(
        id: String,
        page: Int,
    ): MutableLiveData<List<MarketModel>>? {
        if (marketLiveFavData == null) {
            marketLiveFavData = MutableLiveData<List<MarketModel>>()
        }
        loadFavCoinData(id, page)
        return marketLiveFavData
    }

    private fun loadFavCoinData(id: String, page: Int) {
        apiUtils.getMarketData(id, page, object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                val coins: List<MarketModel> = obj as List<MarketModel>
                Log.d(TAG, "onSuccess: $coins")
                marketLiveFavData?.value = coins
            }

            override fun onFailed(msg: String?) {

            }

        })
    }

}