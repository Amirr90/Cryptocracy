package com.e.cryptocracy.viewModel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.cryptocracy.model.MarketModel
import com.e.cryptocracy.repository.Repo

class MyViewModel : ViewModel() {

    var repo: Repo = Repo()
    fun getCoinData(
        id: String?,
        page: Int?,
        activity: Activity?
    ): MutableLiveData<List<MarketModel>>? {
        return repo.getCoinData(id,page, activity)
    }


}