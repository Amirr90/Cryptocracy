package com.e.cryptocracy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.cryptocracy.databinding.CureencyViewBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.utils.CurrencyItem


class CurrencyAdapter(
    private val dataList: List<CurrencyItem>,
    private val adapterInterface: AdapterInterface
) :
    RecyclerView.Adapter<CurrencyAdapter.HomeVH>() {

    class HomeVH(itemView: CureencyViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: CureencyViewBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: CureencyViewBinding = CureencyViewBinding.inflate(inflater, parent, false)
        return HomeVH(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.binding.textView.text = dataList[position].currencyId

        holder.binding.root.setOnClickListener {
            adapterInterface.onItemClick(dataList[position].currencyId)
        }
    }

}