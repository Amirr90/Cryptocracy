package com.e.cryptocracy.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.cryptocracy.R
import com.e.cryptocracy.adapter.MarketAdapter
import com.e.cryptocracy.databinding.FragmentMarketBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.model.MarketModel
import com.e.cryptocracy.utils.App
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils
import com.e.cryptocracy.viewModel.MyViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MarketFragment : Fragment(), AdapterInterface {

    private val TAG = "MarketFragment"

    lateinit var binding: FragmentMarketBinding
    private lateinit var navController: NavController
    private var viewModel: MyViewModel? = null
    private lateinit var coinMarketAdapter: MarketAdapter
    var page = 1
    private var coinsList: List<MarketModel> = ArrayList()
    var favIds: List<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentMarketBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        setupMarketRec(page, "", requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        binding.recMarket.addItemDecoration(
            DividerItemDecoration(

                App.context,
                LinearLayoutManager.VERTICAL
            )
        )


        val gson = Gson()
        val json = AppUtils.getValue(AppConstant.FAV_IDS, requireActivity())
        if (json.isNotEmpty()) {
            val type: Type = object : TypeToken<List<String?>?>() {}.type
            favIds = gson.fromJson(json, type)
            Log.d(TAG, "favIds: $favIds")
        }

        coinMarketAdapter = MarketAdapter(this@MarketFragment, favIds)
        binding.recMarket.adapter = coinMarketAdapter

        binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                Log.d(TAG, "onViewCreated: $page")
                page++
                setupMarketRec(page, "", requireActivity())
            }
        }

    }

    private fun setupMarketRec(page: Int, id: String, activity: Activity) {
        AppUtils.showRequestDialog(activity)
        viewModel?.getCoinData(id, page)
            ?.observe(viewLifecycleOwner) { coinDataList ->
                AppUtils.hideDialog()



                if (coinDataList != null) {
                    for (coin in coinDataList) {
                        Log.d(TAG, "setupMarketRecCoins: " + coin.market_cap_rank + coin.name)
                        if (!coinsList.contains(coin))
                            coinsList = coinsList + coinDataList
                    }
                    Log.d(TAG, "--------------------------")



                    coinMarketAdapter.submitList(coinsList)

                }
            }
    }

    override fun onItemClick(obj: Any) {
        val coinModel = obj as MarketModel
        val bundle = Bundle()
        bundle.putString(AppConstant.COIN_ID, coinModel.id)
        bundle.putString(AppConstant.COIN_NAME, coinModel.name + "(" + coinModel.symbol + ")")
        bundle.putDouble(AppConstant.PRICE, coinModel.current_price)
        navController.navigate(R.id.action_homeFragment_to_coinDetailFragment, bundle)
    }


}