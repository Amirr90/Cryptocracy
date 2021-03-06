package com.e.cryptocracy.utils

import android.app.Activity
import android.util.Log
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.*
import com.e.cryptocracy.model.responseModel.EventResponse
import com.e.cryptocracy.model.responseModel.TrendingCoinResponse
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
        apiCallbackInterface: ApiCallbackInterface,
    ) {
        try {
            val price_change_percentage = "1h,24h,7d"
            val per_page = AppConstant.PER_PAGE_COINS_DATA
            val order: String = AppUtils.getValue(AppConstant.SORT_ORDER, App.context)
            val vs_currency: String = AppUtils.getValue(AppConstant.CURRENCY, App.context)
            //var category: String = AppUtils.getValue(AppConstant.CATEGORY, activity)

            val api = URLUtils.getAPIService()
            val specialityResCall = api.coinMarketData(
                vs_currency,
                id,
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

    fun get_OHLC_Data(
        id: String,
        duration: String,
        apiCallbackInterface: ApiCallbackInterface,
    ) {
        try {
            val currency = AppUtils.getValue(AppConstant.CURRENCY, App.context)
            val api = URLUtils.getAPIService()
            val specialityResCall = api.get_OHLC_Data(id, duration, currency)
            specialityResCall.enqueue(object : Callback<ArrayList<ArrayList<String>>> {
                override fun onResponse(
                    call: Call<ArrayList<ArrayList<String>>>,
                    response: Response<ArrayList<ArrayList<String>>>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                        Log.d(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ArrayList<String>>>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                    Log.d(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getExchangeData(page: String, apiCallbackInterface: ApiCallbackInterface) {
        try {
            val api = URLUtils.getAPIService()
            val specialityResCall =
                api.getExchangeData(AppConstant.PER_PAGE_COINS_DATA.toString(), page)
            specialityResCall.enqueue(object : Callback<List<ExchangeModel>> {
                override fun onResponse(
                    call: Call<List<ExchangeModel>>,
                    response: Response<List<ExchangeModel>>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                        Log.d(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(call: Call<List<ExchangeModel>>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                    Log.d(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCountryList(apiCallbackInterface: ApiCallbackInterface) {
        try {
            val api = URLUtils.getAPIService()
            val specialityResCall =
                api.countries()
            specialityResCall.enqueue(object : Callback<CountryList> {
                override fun onResponse(
                    call: Call<CountryList>,
                    response: Response<CountryList>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                        Log.d(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(call: Call<CountryList>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                    Log.d(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getEventType(apiCallbackInterface: ApiCallbackInterface) {
        try {
            val api = URLUtils.getAPIService()
            val specialityResCall =
                api.eventData
            specialityResCall.enqueue(object : Callback<EventTypeModel> {
                override fun onResponse(
                    call: Call<EventTypeModel>,
                    response: Response<EventTypeModel>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                        Log.d(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(call: Call<EventTypeModel>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                    Log.d(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun getEventData(page: Int, apiCallbackInterface: ApiCallbackInterface) {
        try {

            val type = AppUtils.getValue(AppConstant.EVENT_TYPE, App.context)
            val country_code = AppUtils.getValue(AppConstant.EVENT_COUNTRY_CODE, App.context)
            val upcoming_events_only = false
         /*   val from_date = ""
            val to_date = ""*/
            val api = URLUtils.getAPIService()
            val specialityResCall =
                api.getEventData(country_code,
                    type,
                    page,
                    upcoming_events_only)
            specialityResCall.enqueue(object : Callback<EventResponse> {
                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                        Log.d(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                    Log.d(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun derivativeData(includeTickers: String, apiCallbackInterface: ApiCallbackInterface) {
        try {


            val api = URLUtils.getAPIService()
            val specialityResCall =
                api.derivativeData(includeTickers)
            specialityResCall.enqueue(object : Callback<List<DerivativeModel>> {
                override fun onResponse(
                    call: Call<List<DerivativeModel>>,
                    response: Response<List<DerivativeModel>>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                        Log.d(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(call: Call<List<DerivativeModel>>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                    Log.d(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadTendingCoinData(apiCallbackInterface: ApiCallbackInterface) {
        try {

            val api = URLUtils.getAPIService()
            val specialityResCall =
                api.loadTendingCoinData()
            specialityResCall.enqueue(object : Callback<TrendingCoinResponse> {
                override fun onResponse(
                    call: Call<TrendingCoinResponse>,
                    response: Response<TrendingCoinResponse>,
                ) {
                    if (response.code() == 200) {
                        if (null != response.body()) apiCallbackInterface.onSuccess(response.body()!!)
                        Log.d(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(call: Call<TrendingCoinResponse>, t: Throwable) {
                    apiCallbackInterface.onFailed(t.localizedMessage)
                    Log.d(TAG, "onFailure: " + t.localizedMessage)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

