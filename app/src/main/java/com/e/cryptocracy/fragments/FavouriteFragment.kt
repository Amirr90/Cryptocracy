package com.e.cryptocracy.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.cryptocracy.R
import com.e.cryptocracy.adapter.MarketFavAdapter
import com.e.cryptocracy.databinding.FragmentFavouriteBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.model.MarketModel
import com.e.cryptocracy.utils.App
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils
import com.e.cryptocracy.viewModel.MyViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class FavouriteFragment : Fragment(), AdapterInterface {
    private val PAGE: Int = 1
    private val TAG = "FavouriteFragment"


    lateinit var binding: FragmentFavouriteBinding
    lateinit var navController: NavController
    var viewModel: MyViewModel? = null
    lateinit var coinMarketAdapter: MarketFavAdapter
    var favIds: List<String> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavouriteBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        binding.revFavourite.addItemDecoration(
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


        coinMarketAdapter = MarketFavAdapter(this@FavouriteFragment, favIds)
        binding.revFavourite.adapter = coinMarketAdapter

        getFavIds()



        binding.refreshFav.setOnRefreshListener {

        }

        binding.refreshFav.setOnRefreshListener {
            getFavIds()
        }

    }

    private fun getFavIds() {
        AppUtils.getFireStoreReference().collection(AppConstant.USERS).document(AppUtils.getUid())
            .collection(AppConstant.FAVOURITE)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                val list = ArrayList<String>()
                var favIds = ""
                for (doc in value!!) {
                    favIds += doc.id + ","
                    list += doc.id
                }
                Log.d(TAG, "Current ids: $favIds")

                val gson = Gson()
                val json: String = gson.toJson(list)
                AppUtils.setValue(AppConstant.FAV_IDS, json, requireActivity())
                setupMarketRec(PAGE, favIds)
            }

    }

    private fun setupMarketRec(page: Int, id: String) {
        viewModel?.getFavCoinData(id, page)
            ?.observe(viewLifecycleOwner) { coinDataList ->

                if (binding.refreshFav.isRefreshing) {
                    binding.refreshFav.isRefreshing = false
                    AppConstant.showToast("Refreshed !!")
                }

                if (coinDataList != null) {
                    coinMarketAdapter.submitList(coinDataList)
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