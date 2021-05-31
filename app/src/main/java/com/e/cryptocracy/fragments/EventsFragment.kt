package com.e.cryptocracy.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.e.cryptocracy.adapter.EventsAdapter
import com.e.cryptocracy.databinding.FragmentEventsBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.CountryList
import com.e.cryptocracy.model.ExchangeModel
import com.e.cryptocracy.utils.APIUtils
import com.e.cryptocracy.utils.AppUtils

class EventsFragment : Fragment(), AdapterInterface {
    private val TAG = "EventsFragment"

    lateinit var binder: FragmentEventsBinding
    lateinit var navController: NavController
    lateinit var adapter: EventsAdapter
    private val utilsLight = APIUtils()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binder = FragmentEventsBinding.inflate(layoutInflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        getCountryData()
        initRec()
    }

    private fun getCountryData() {
        utilsLight.getCountryList(object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                AppUtils.hideDialog()
                val exchangeList = obj as CountryList
                Log.d(TAG, "onSuccess: " + exchangeList.data[1].country)
                //adapter.addItems(exchangeList)
            }

            override fun onFailed(msg: String) {

            }

        })
    }


    private fun initRec() {
        adapter = EventsAdapter(this)
        binder.recEvents.adapter = adapter

        getData("1")
    }

    private fun getData(page: String) {


    }

    override fun onItemClick(obj: Any) {

    }
}