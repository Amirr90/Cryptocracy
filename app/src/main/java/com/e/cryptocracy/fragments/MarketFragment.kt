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
import com.e.cryptocracy.adapter.MarketAdapter
import com.e.cryptocracy.databinding.FragmentMarketBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.model.MarketModel
import com.e.cryptocracy.utils.App
import com.e.cryptocracy.viewModel.MyViewModel

class MarketFragment : Fragment(), AdapterInterface {
    private val TAG = "MarketFragment"


    private lateinit var binding: FragmentMarketBinding
    lateinit var navController: NavController
    var viewModel: MyViewModel? = null
    lateinit var coinAdapter: MarketAdapter
    var coinList: List<MarketModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMarketBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]
        setupMarketRec()
    }

    private fun setupMarketRec() {


        var id = ""
        var page = 1
        viewModel?.getCoinData(id, page, requireActivity())
            ?.observe(viewLifecycleOwner) { coinDataList ->
                if (coinDataList != null) {
                    for (element in coinDataList) {
                        coinList += element
                        Log.d(TAG, "currency: $coinList")
                    }


                    coinAdapter = MarketAdapter(coinList, this)
                    binding.recMarket.addItemDecoration(
                        DividerItemDecoration(

                            App.context,
                            LinearLayoutManager.VERTICAL
                        )
                    )
                    binding.recMarket.adapter = coinAdapter

                    Log.d(TAG, "setupMarketRec: $coinDataList")
                }
            }
    }

    override fun onItemClick(obj: Any) {
        Log.d(TAG, "onItemClick: $obj")
    }

}