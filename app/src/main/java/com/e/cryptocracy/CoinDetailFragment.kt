package com.e.cryptocracy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.e.cryptocracy.databinding.FragmentCoinDetailBinding
import com.e.cryptocracy.fragments.coins.*
import com.e.cryptocracy.utils.AppConstant
import com.google.android.material.tabs.TabLayout
import java.util.*
import kotlin.properties.Delegates


class CoinDetailFragment : Fragment() {
    private val TAG = "CoinDetailFragment"

    lateinit var binding: FragmentCoinDetailBinding
    private lateinit var navController: NavController
    lateinit var id: String
    var price by Delegates.notNull<Double>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCoinDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        id = arguments?.getString(AppConstant.COIN_ID).toString()
        price = arguments?.getDouble(AppConstant.PRICE)!!

        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            arguments?.getString(AppConstant.COIN_NAME).toString()

        setupFragments()
    }

    private fun setupFragments() {
        setUpTabbedView()
        setUpViewPager()
    }

    override fun onResume() {
        super.onResume()
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!.show()
    }

    private fun setUpTabbedView() {
        binding.tabLayout2.tabGravity = TabLayout.GRAVITY_START
    }

    private fun setUpViewPager() {
        val adapter =
            MyAdapter(
                requireContext(),
                this@CoinDetailFragment.childFragmentManager,
                id, price
            )
        binding.coinViewPager.adapter = adapter

        binding.coinViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(
            binding.tabLayout2))
        binding.tabLayout2.setupWithViewPager(binding.coinViewPager)
        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.coinViewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


    }


    class MyAdapter(
        private val mContext: Context,
        fm: FragmentManager?,
        var id: String,
        var price: Double,
    ) :
        FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> {
                    return PriceChartFragment(id, price)
                }
                1 -> {
                    return CoinExchangesFragment()
                }
                2 -> {
                    return CoinPortFolioFragment()
                }
                3 -> {
                    return CoinInforFragment()
                }

                else -> return CoinEventsFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mContext.resources.getString(TAB_TITLES[position])
        }

        override fun getCount(): Int {
            return 5
        }

        companion object {
            @StringRes
            private val TAB_TITLES =
                intArrayOf(
                    R.string.price_chart,
                    R.string.exchanges,
                    R.string.portfolio,
                    R.string.info,
                    R.string.events
                )
        }
    }

}