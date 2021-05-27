package com.e.cryptocracy.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.e.cryptocracy.R
import com.e.cryptocracy.fragments.*

class MyAdapter(private val mContext: Context, fm: FragmentManager?, var totalTabs: Int) :
    FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return MarketFragment()
            }
            1 -> {
                return FavouriteFragment()
            }
            2 -> {
                return ExchangeFragment()
            }
            3 -> {
                return EventsFragment()
            }

            else -> return DerivativeFragment()
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
                R.string.market,
                R.string.favourite,
                R.string.exchanges,
                R.string.events,
                R.string.derivations
            )
    }
}