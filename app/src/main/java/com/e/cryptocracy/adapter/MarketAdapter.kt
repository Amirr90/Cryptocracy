package com.e.cryptocracy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.cryptocracy.databinding.MarketViewBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.model.MarketModel


class MarketAdapter(
    private val dataList: List<MarketModel>,
    private val adapterInterface: AdapterInterface,
) :
    RecyclerView.Adapter<MarketAdapter.HomeVH>() {

    class HomeVH(itemView: MarketViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: MarketViewBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: MarketViewBinding = MarketViewBinding.inflate(inflater, parent, false)
        return HomeVH(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: HomeVH, position: Int) {

        holder.binding.coinModel = dataList[position]
        holder.binding.position = (position + 1).toString()

        holder.binding.mainCoinsLayout.setOnClickListener {
            adapterInterface.onItemClick(dataList[position])
        }
    }

}

