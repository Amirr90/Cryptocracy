package com.e.cryptocracy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.cryptocracy.databinding.DerivativeViewBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.model.DerivativeModel


class DerivativeAdapter(
    private
    val adapterInterface: AdapterInterface,
) :
    RecyclerView.Adapter<DerivativeAdapter.HomeVH>() {

    private val TAG = "EventsAdapter"
    private var dataList: List<DerivativeModel> = ArrayList()

    class HomeVH(itemView: DerivativeViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: DerivativeViewBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: DerivativeViewBinding = DerivativeViewBinding.inflate(inflater, parent, false)
        return HomeVH(binding)
    }


    fun addItem(item: DerivativeModel) {
        dataList += item
        notifyDataSetChanged()
    }

    fun addItems(list: List<DerivativeModel>) {
        Log.d(TAG, "addItems: $list")
        dataList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {

        val derivative = dataList[position]
        derivative.position = (position + 1).toString()
        holder.binding.derivative = derivative


        holder.binding.mainDerivativeLay.setOnClickListener {
            adapterInterface.onItemClick(dataList[position])
        }
    }

}

