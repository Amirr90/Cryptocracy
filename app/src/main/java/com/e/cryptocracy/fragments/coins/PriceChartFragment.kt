package com.e.cryptocracy.fragments.coins

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.cryptocracy.R
import com.e.cryptocracy.databinding.FragmentPriceChartBinding
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.CoinModel
import com.e.cryptocracy.utils.APIUtils
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils


class PriceChartFragment(var id: String, var price: Double) : Fragment() {
    private val TAG = "PriceChartFragment"

    lateinit var binding: FragmentPriceChartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPriceChartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCoinData(id)
    }

    private fun getCoinData(id: String) {
        val apiUtils = APIUtils()
        apiUtils.getCoinDataById(id, object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {

                binding.textView20.text = AppUtils.getCurrencyFormat(price)
                val coins: CoinModel = obj as CoinModel
                Log.d(TAG, "onSuccess: $price")
                coins.current_price = price
                binding.coin = coins

                binding.tvCurrency.text = AppUtils.getValue(AppConstant.CURRENCY, requireActivity())
            }

            override fun onFailed(msg: String) {
                AppConstant.showToast(getString(R.string.try_again))
            }

        })
    }
}