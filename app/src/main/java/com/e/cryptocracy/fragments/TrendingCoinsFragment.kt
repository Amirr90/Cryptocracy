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
import com.e.cryptocracy.adapter.TrendingCoinsAdapter
import com.e.cryptocracy.databinding.FragmentTrendingCoinsBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.utils.App
import com.e.cryptocracy.utils.AppUtils
import com.e.cryptocracy.viewModel.MyViewModel


class TrendingCoinsFragment : Fragment(), AdapterInterface {
    private  val TAG = "TrendingCoinsFragment"

    lateinit var binding: FragmentTrendingCoinsBinding
    lateinit var navController: NavController
    var viewModel: MyViewModel? = null
    lateinit var adapter: TrendingCoinsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTrendingCoinsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        binding.recTrendingCoins.addItemDecoration(
            DividerItemDecoration(
                App.context,
                LinearLayoutManager.VERTICAL
            )
        )
        adapter = TrendingCoinsAdapter(this@TrendingCoinsFragment)
        binding.recTrendingCoins.adapter = adapter

        setupTrendingRec()
    }

    private fun setupTrendingRec() {
        AppUtils.showRequestDialog(requireActivity())
        viewModel?.getTrendingCoinData()
            ?.observe(viewLifecycleOwner) { coinDataList ->
                AppUtils.hideDialog()
                if (coinDataList != null) {
                    adapter.addItems(coinDataList.coins)
                }
            }
    }

    override fun onItemClick(obj: Any) {
        Log.d(TAG, "onItemClick: $obj")
    }
}