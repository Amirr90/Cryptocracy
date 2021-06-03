package com.e.cryptocracy.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.cryptocracy.model.MarketModel
import com.e.cryptocracy.model.responseModel.TrendingCoinResponse
import com.e.cryptocracy.repository.Repo
import com.e.cryptocracy.utils.APIUtils

class MyViewModel : ViewModel() {

    var repo: Repo = Repo(APIUtils())
    fun getCoinData(
        id: String?,
        page: Int?,
    ): MutableLiveData<List<MarketModel>>? {
        return repo.getCoinData(id, page)
    }

    fun getTrendingCoinData(): MutableLiveData<TrendingCoinResponse>? {
        return repo.getTrendingCoinData()
    }

    fun getFavCoinData(
        id: String,
        page: Int,
    ): MutableLiveData<List<MarketModel>>? {
        return repo.getFavCoinData(id, page)
    }

}

