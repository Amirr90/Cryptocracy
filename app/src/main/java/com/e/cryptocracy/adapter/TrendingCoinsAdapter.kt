package com.e.cryptocracy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.cryptocracy.databinding.TrendingCoinViewBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.model.responseModel.TrendingCoinResponse


class TrendingCoinsAdapter(
    private
    val adapterInterface: AdapterInterface,
) :
    RecyclerView.Adapter<TrendingCoinsAdapter.HomeVH>() {

    private val TAG = "EventsAdapter"
    private var dataList: List<TrendingCoinResponse.Coins> = ArrayList()

    class HomeVH(itemView: TrendingCoinViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: TrendingCoinViewBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: TrendingCoinViewBinding =
            TrendingCoinViewBinding.inflate(inflater, parent, false)
        return HomeVH(binding)
    }

    fun addItems(list: List<TrendingCoinResponse.Coins>) {
        Log.d(TAG, "addItems: $list")
        dataList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {

        val coinModel = dataList[position]
        holder.binding.coinModel = coinModel


        holder.binding.mainDerivativeLay.setOnClickListener {
            adapterInterface.onItemClick(dataList[position])
        }
    }

}

