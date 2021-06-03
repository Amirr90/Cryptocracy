package com.e.cryptocracy.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.cryptocracy.R
import com.e.cryptocracy.adapter.EventsAdapter
import com.e.cryptocracy.databinding.FragmentEventsBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.CountryList
import com.e.cryptocracy.model.EventTypeModel
import com.e.cryptocracy.model.responseModel.EventResponse
import com.e.cryptocracy.utils.APIUtils
import com.e.cryptocracy.utils.App
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils


class EventsFragment : Fragment(), AdapterInterface {
    private val TAG = "EventsFragment"

    lateinit var binder: FragmentEventsBinding
    lateinit var navController: NavController
    lateinit var adapter: EventsAdapter
    private val utilsLight = APIUtils()

    var code = "US"

    private lateinit var builder: AlertDialog.Builder
    private lateinit var eventBuilder: AlertDialog.Builder

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

        builder = AlertDialog.Builder(requireActivity())
        eventBuilder = AlertDialog.Builder(requireActivity())


        binder.tvCountry.text = AppUtils.getValue(AppConstant.EVENT_COUNTRY, requireActivity())
        binder.tvEvent.text = AppUtils.getValue(AppConstant.EVENT_TYPE, requireActivity())

        getCountryData()

        initRec()

        binder.tvCountry.setOnClickListener {
            builder.show()

        }

        binder.tvEvent.setOnClickListener {
            eventBuilder.show()
        }
    }

    private fun initEventData() {
        utilsLight.getEventType(object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                val eventModel = obj as EventTypeModel

                val items = arrayOfNulls<CharSequence>(eventModel.count)

                for (a in 0 until eventModel.count) {
                    try {
                        Log.d(TAG, "AddEvents: " + eventModel.data[a])
                        items[a] = eventModel.data[a]

                    } catch (e: Exception) {
                        Log.d(TAG, "initEventData: " + e.localizedMessage)
                    }
                }

                eventBuilder.setTitle(R.string.select_event_type)
                eventBuilder.setItems(items)
                { dialog, item ->
                    AppUtils.setValue(AppConstant.EVENT_TYPE,
                        eventModel.data[item],
                        requireActivity())
                    Log.d(TAG, "initDialog: eventType " + eventModel.data[item])

                    binder.tvEvent.text = eventModel.data[item]
                    dialog.dismiss()
                    getData(1)
                }

            }

            override fun onFailed(msg: String?) {
                val items = arrayOfNulls<CharSequence>(3)
                items[0] = AppConstant.EventType.EVENT
                items[1] = AppConstant.EventType.CONFERENCE
                items[2] = AppConstant.EventType.MEETUP

                eventBuilder.setTitle(R.string.select_event_type)
                eventBuilder.setItems(items)
                { dialog, item ->
                    AppUtils.setValue(AppConstant.EVENT_TYPE,
                        items[item].toString(),
                        requireActivity())
                    Log.d(TAG, "initDialog: eventType " + items[item].toString())
                    binder.tvEvent.text = items[item]
                    dialog.dismiss()
                }

            }

        })


    }

    private fun getCountryData() {
        utilsLight.getCountryList(object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                AppUtils.hideDialog()
                val exchangeList = obj as CountryList
                Log.d(TAG, "onSuccess: " + exchangeList.data[1].country)

                initDialog(exchangeList)
                initEventData()
            }

            override fun onFailed(msg: String?) {

            }

        })
    }

    private fun initDialog(exchangeList: CountryList) {
        val items = arrayOfNulls<CharSequence>(exchangeList.data.size - 1)

        for (a in 1 until exchangeList.data.size) {
            try {
                Log.d(TAG, "AddItems: " + exchangeList.data[a].country)
                items[a - 1] = exchangeList.data[a].country

            } catch (e: Exception) {
                Log.d(TAG, "initDialog: " + e.localizedMessage)
            }
        }

        builder.setTitle(R.string.select_country)
        builder.setItems(items) { dialog, item ->
            val country = exchangeList.data[item + 1].country
            code = exchangeList.data[item + 1].code

            AppUtils.setValue(AppConstant.EVENT_COUNTRY,
                country,
                requireActivity())


            AppUtils.setValue(AppConstant.EVENT_COUNTRY_CODE,
                code,
                requireActivity())



            Log.d(TAG, "initDialog: country $country   code: $code")
            binder.tvCountry.text = country
            dialog.dismiss()
            getData(1)
        }


    }


    private fun initRec() {
        adapter = EventsAdapter(this)
        binder.recEvents.adapter = adapter
        binder.recEvents.addItemDecoration(
            DividerItemDecoration(

                App.context,
                LinearLayoutManager.VERTICAL
            )
        )
        getData(1)
    }

    private fun getData(page: Int) {
        utilsLight.getEventData(page, object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                val eventModel = obj as EventResponse


                if (eventModel.data.isEmpty()) {
                    AppConstant.showToast("No data found !! ")
                    return
                }

                adapter.addItems(eventModel.data)
                Log.d(TAG, "Added: " + eventModel.data)

            }

            override fun onFailed(msg: String?) {
                Log.d(TAG, "onFailed: $msg")
                AppConstant.showToast("try again !! ")
            }

        })
    }

    override fun onItemClick(obj: Any) {

    }
}