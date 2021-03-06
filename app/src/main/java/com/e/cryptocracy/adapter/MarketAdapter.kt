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
import com.e.cryptocracy.model.MarketModel.itemCallback
import com.e.cryptocracy.utils.App
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils
import com.google.gson.Gson

class MarketAdapter(
    private val adapterInterface: AdapterInterface,
    private var favIds: List<String>,
) :
    ListAdapter<MarketModel, MarketAdapter.VitalVH?>(itemCallback) {
    private val TAG = "MarketAdapter"
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
                    val msg = obj as String
                    favIds = favIds + coins.id
                    val gson = Gson()
                    val json: String = gson.toJson(favIds)
                    AppUtils.setValue(AppConstant.FAV_IDS, json, App.context)
                    AppConstant.showToast(msg)
                }

                override fun onFailed(msg: String?) {
                    holder.binding.checkBox.isChecked = false
                    AppConstant.showToast("try again !!")
                }

            })
        }
    }

    class VitalVH(val binding: MarketViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}
