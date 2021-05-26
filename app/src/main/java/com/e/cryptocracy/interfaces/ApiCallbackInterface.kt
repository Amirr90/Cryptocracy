package com.e.cryptocracy.interfaces

interface ApiCallbackInterface {
    fun onSuccess(obj: Any)
    fun onFailed(msg: String)
}