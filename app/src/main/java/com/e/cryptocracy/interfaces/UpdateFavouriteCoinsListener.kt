package com.e.cryptocracy.interfaces

interface UpdateFavouriteCoinsListener {
    fun onSuccess(obj: Any)
    fun onFailed(msg: String?)
}