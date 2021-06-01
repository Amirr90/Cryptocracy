package com.e.cryptocracy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.cryptocracy.databinding.EventViewBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.model.responseModel.EventResponse


class EventsAdapter(


            private
            val adapterInterface: AdapterInterface,
) :
    RecyclerView.Adapter<EventsAdapter.HomeVH>() {

    private  val TAG = "EventsAdapter"
    private var dataList: List<EventResponse.Event> = ArrayList()

    class HomeVH(itemView: EventViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: EventViewBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: EventViewBinding = EventViewBinding.inflate(inflater, parent, false)
        return HomeVH(binding)
    }


    fun addItem(item: EventResponse.Event) {
        if (null == dataList)
            dataList = ArrayList()
        dataList += item
        notifyDataSetChanged()
    }

    fun addItems(list: List<EventResponse.Event>) {
        Log.d(TAG, "addItems: $list")
        dataList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {

        val eventModel = dataList[position]
        //eventModel.position = (position + 1).toString()
        holder.binding.event = eventModel


        holder.binding.mailEventLay.setOnClickListener {
            adapterInterface.onItemClick(dataList[position])
        }
    }

}

