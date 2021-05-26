package com.e.cryptocracy.utils

import com.e.cryptocracy.interfaces.ApiCallbackInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class APIUtils {
    fun getAllCurrency(apiCallbackInterface: ApiCallbackInterface) {
        try {
            val api = URLUtils.getAPIService()
            val specialityResCall = api.allCurrency
            specialityResCall.enqueue(object : Callback<ArrayList<String>?> {
                override fun onResponse(
                    call: Call<ArrayList<String>?>, response: Response<ArrayList<String>?>
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
}