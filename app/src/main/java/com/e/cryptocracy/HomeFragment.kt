package com.e.cryptocracy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.e.cryptocracy.adapter.MyAdapter
import com.e.cryptocracy.databinding.FragmentHomeBinding
import com.e.cryptocracy.fragments.MarketFragment
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppConstant.showToast
import com.e.cryptocracy.utils.AppUtils
import com.e.cryptocracy.utils.SortOrder
import com.e.cryptocracy.view.activity.SelectCurrencyActivity
import com.google.android.material.tabs.TabLayout
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {


    private val TAG = "HomeFragment"
    lateinit var binding: FragmentHomeBinding
    var navController: NavController? = null
    lateinit var marketFragment: MarketFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        setUpTabbedView()
        setUpViewPager()



        AppUtils.updateCurrency(binding.tvCurrency, requireActivity())

        binding.tvCurrency.setOnClickListener {
            startActivity(Intent(requireActivity(), SelectCurrencyActivity::class.java))
        }
        binding.ivFilter.setOnClickListener {
            showFilterDialog()
        }

    }

    private fun showFilterDialog() {
        val items = arrayOf<CharSequence>("Market Cap Ascending", "Market Cap Descending",
            "Coin Gecko Ascending", "Coin Gecko Descending", "Volume Ascending", "Volume descending"
        )

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Make your selection")
        builder.setItems(items
        ) { dialog, item ->
            showToast("Sorting: " + items[item])
            dialog.dismiss()
            AppUtils.setValue(AppConstant.SORT_ORDER, getData()[item], requireActivity())
            Log.d(TAG, "showFilterDialog: " + AppUtils.getValue(AppConstant.SORT_ORDER, activity))
            marketFragment = MarketFragment()
            marketFragment.setupMarketRec(1, "")
        }.show()
    }

    private fun getData(): List<String> {
        var list: List<String> = ArrayList()
        list += SortOrder.market_cap_asc
        list += SortOrder.market_cap_desc
        list += SortOrder.gecko_asc
        list += SortOrder.gecko_desc
        list += SortOrder.volume_asc
        list += SortOrder.volume_desc
        list += SortOrder.id_asc
        list += SortOrder.id_desc
        return list
    }

    private fun setUpTabbedView() {
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_START
    }

    private fun setUpViewPager() {
        val adapter =
            MyAdapter(
                requireContext(),
                requireActivity().supportFragmentManager,
                binding.tabLayout.tabCount
            )
        binding.viewPager.adapter = adapter

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


    }


    override fun onResume() {
        super.onResume()
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!.hide()
    }
}