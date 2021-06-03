package com.e.cryptocracy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.cryptocracy.databinding.ExchangeViewBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.model.ExchangeModel


class ExchangeAdapter(

    private val adapterInterface: AdapterInterface,
) :
    RecyclerView.Adapter<ExchangeAdapter.HomeVH>() {

    private var dataList: List<ExchangeModel> = ArrayList()

    class HomeVH(itemView: ExchangeViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: ExchangeViewBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ExchangeViewBinding = ExchangeViewBinding.inflate(inflater, parent, false)
        return HomeVH(binding)
    }


    fun addItem(item: ExchangeModel) {
        dataList += item
        notifyDataSetChanged()
    }

    fun addItems(list: List<ExchangeModel>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {

        val exchangeModel = dataList[position]
        exchangeModel.position = (position + 1).toString()
        holder.binding.exchange = exchangeModel


        holder.binding.mainExchangeView.setOnClickListener {
            adapterInterface.onItemClick(dataList[position])
        }
    }

}

