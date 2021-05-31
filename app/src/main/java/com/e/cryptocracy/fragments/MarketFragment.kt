package com.e.cryptocracy.fragments

import android.os.Bundle
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
import com.e.cryptocracy.adapter.MarketAdapter
import com.e.cryptocracy.databinding.FragmentMarketBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.model.MarketModel
import com.e.cryptocracy.utils.App
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils
import com.e.cryptocracy.viewModel.MyViewModel

class MarketFragment : Fragment(), AdapterInterface {
    private val TAG = "MarketFragment"


    lateinit var binding: FragmentMarketBinding
    lateinit var navController: NavController
    var viewModel: MyViewModel? = null
    var coinList: List<MarketModel> = ArrayList()
    lateinit var coinMarketAdapter: MarketAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentMarketBinding.inflate(layoutInflater)
        return binding.root
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
        coinMarketAdapter = MarketAdapter(coinList, this@MarketFragment)
        binding.recMarket.adapter = coinMarketAdapter

        setupMarketRec(1, "")

    }

    fun setupMarketRec(page: Int, id: String) {
        AppUtils.showRequestDialog(requireActivity())
        viewModel?.getCoinData(id, page, requireActivity())
            ?.observe(viewLifecycleOwner) { coinDataList ->
                AppUtils.hideDialog()
                if (coinDataList != null) {
                    for (element in coinDataList) {
                        coinList += element
                    }
                    coinMarketAdapter.addItems(coinList)
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