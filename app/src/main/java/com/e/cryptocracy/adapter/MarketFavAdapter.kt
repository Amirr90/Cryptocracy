package com.e.cryptocracy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.e.cryptocracy.databinding.MarketViewBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.interfaces.UpdateFavouriteCoinsListener
import com.e.cryptocracy.model.MarketModel
import com.e.cryptocracy.utils.AppUtils


class MarketFavAdapter
    (
    private val adapterInterface: AdapterInterface,
    private val favIds: List<String>,
) :
    ListAdapter<MarketModel, MarketFavAdapter.VitalVH?>(MarketModel.itemCallback) {
    private val TAG = "MarketFavAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VitalVH {
        val inflater = LayoutInflater.from(parent.context)
        val vitalViewBinding: MarketViewBinding = MarketViewBinding.inflate(inflater, parent, false)
        return VitalVH(vitalViewBinding)
    }

    override fun onBindViewHolder(holder: VitalVH, position: Int) {
        val coins = getItem(position)
        holder.binding.coinModel = getItem(position)
        holder.binding.position = (position + 1).toString()

        holder.binding.mainCoinsLayout.setOnClickListener {
            adapterInterface.onItemClick(coins)
        }
        if (favIds.isNotEmpty())
            holder.binding.checkBox.isChecked = favIds.contains(coins.id)

        holder.binding.checkBox.setOnClickListener {
            Log.d(TAG, "onBindViewHolder: " + coins.id)
            Log.d(TAG, "isChecked: " + holder.binding.checkBox.isChecked)

            AppUtils.updateFavCoins(coins.id, holder.binding.checkBox.isChecked, object :
                UpdateFavouriteCoinsListener {
                override fun onSuccess(obj: Any) {
                    notifyDataSetChanged()
                }

                override fun onFailed(msg: String?) {
                    notifyDataSetChanged()
                }

            })
        }
    }

    class VitalVH(val binding: MarketViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}
