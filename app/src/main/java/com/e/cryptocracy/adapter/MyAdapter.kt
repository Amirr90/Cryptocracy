package com.e.cryptocracy.adapter

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.e.cryptocracy.fragments.ExchangeFragment
import com.e.cryptocracy.fragments.FavouriteFragment
import com.e.cryptocracy.fragments.MarketFragment


class MyAdapter(fm: FragmentManager, var totalTabs: Int) :
    FragmentStateAdapter(fm) {


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                MarketFragment()
            }
            1 -> {
                FavouriteFragment()
            }
            else -> return ExchangeFragment()
        }
    }

}