package com.e.cryptocracy.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.e.cryptocracy.adapter.ExchangeAdapter
import com.e.cryptocracy.databinding.FragmentExchangeBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.ExchangeModel
import com.e.cryptocracy.utils.APIUtils
import com.e.cryptocracy.utils.AppUtils


class ExchangeFragment : Fragment(), AdapterInterface {
    private val TAG = "ExchangeFragment"


    lateinit var binder: FragmentExchangeBinding
    lateinit var navController: NavController
    lateinit var adapter: ExchangeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binder = FragmentExchangeBinding.inflate(layoutInflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initRec()
    }

    private fun initRec() {
        adapter = ExchangeAdapter(this)
        binder.recExchange.adapter = adapter

        getData("1")
    }

    private fun getData(page: String) {
        val utilsLight = APIUtils()
        utilsLight.getExchangeData(page, object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                AppUtils.hideDialog()
                val exchangeList = obj as List<ExchangeModel>
                Log.d(TAG, "onSuccess: " + exchangeList.size)
                adapter.addItems(exchangeList)
            }

            override fun onFailed(msg: String?) {

            }

        })
    }

    override fun onItemClick(obj: Any) {

    }

}