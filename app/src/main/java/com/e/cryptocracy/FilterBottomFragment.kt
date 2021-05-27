package com.e.cryptocracy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.cryptocracy.databinding.FilterViewBinding
import com.e.cryptocracy.databinding.FragmentFilterBottomBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.utils.App
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils
import com.e.cryptocracy.utils.SortOrder
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomFragment : BottomSheetDialogFragment(), AdapterInterface {

    lateinit var binding: FragmentFilterBottomBinding
    private lateinit var filterAdapter: FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBottomBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecView()
    }

    private fun setupRecView() {
        filterAdapter = FilterAdapter(getData(), this)
        binding.recFilter.addItemDecoration(
            DividerItemDecoration(

                App.context,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.recFilter.adapter = filterAdapter
    }

    private fun getData(): List<String> {
        var list: List<String> = ArrayList()
        list += SortOrder.market_cap_desc
        list += SortOrder.market_cap_asc
        list += SortOrder.gecko_desc
        list += SortOrder.gecko_asc
        list += SortOrder.volume_asc
        list += SortOrder.volume_desc
        list += SortOrder.id_asc
        list += SortOrder.id_desc
        return list
    }

    override fun onItemClick(obj: Any) {
        val sortOrder = obj as String
        AppUtils.setValue(AppConstant.SORT_ORDER, sortOrder, requireActivity())
        dismiss()

    }
}


class FilterAdapter(
    private val filterDataList: List<String>,
    private val adapterInterface: AdapterInterface
) :
    RecyclerView.Adapter<FilterAdapter.HomeVH>() {

    class HomeVH(itemView: FilterViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: FilterViewBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: FilterViewBinding = FilterViewBinding.inflate(inflater, parent, false)
        return HomeVH(binding)
    }

    override fun getItemCount() = filterDataList.size

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.binding.textView4.text = filterDataList[position]
        holder.binding.root.setOnClickListener {
            adapterInterface.onItemClick(filterDataList[position])
        }
    }

}
