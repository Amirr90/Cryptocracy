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
import com.e.cryptocracy.adapter.DerivativeAdapter
import com.e.cryptocracy.databinding.FragmentDerivativeBinding
import com.e.cryptocracy.interfaces.AdapterInterface
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.DerivativeModel
import com.e.cryptocracy.utils.APIUtils
import com.e.cryptocracy.utils.App
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils

class DerivativeFragment : Fragment(), AdapterInterface {

    private val TAG = "DerivativeFragment"

    lateinit var binder: FragmentDerivativeBinding
    lateinit var navController: NavController
    lateinit var adapter: DerivativeAdapter
    private val utilsLight = APIUtils()
    lateinit var include_tickers: String

    private lateinit var derivativeBuilder: AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binder = FragmentDerivativeBinding.inflate(layoutInflater)
        return binder.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        derivativeBuilder = AlertDialog.Builder(requireActivity())

        binder.tvDerivativeType.text =
            AppUtils.getValue(AppConstant.DERIVATIVE_TYPE, requireActivity())

        initDerivativeType()

        initRec()



        binder.tvDerivativeType.setOnClickListener {
            derivativeBuilder.show()
        }

        binder.swipeDerivative.setOnRefreshListener {
            getDerivativeData(AppUtils.getValue(AppConstant.DERIVATIVE_TYPE, requireActivity()))
        }

    }

    private fun initDerivativeType() {
        val items = arrayOfNulls<CharSequence>(2)
        items[0] = AppConstant.DerivativeType.ALL
        items[1] = AppConstant.DerivativeType.UNEXPIRED


        derivativeBuilder.setTitle(R.string.select_event_type)
        derivativeBuilder.setItems(items)
        { dialog, item ->
            AppUtils.setValue(AppConstant.DERIVATIVE_TYPE,
                items[item].toString(),
                requireActivity())
            Log.d(TAG, "initDialog: eventType " + items[item].toString())
            binder.tvDerivativeType.text = items[item]
            dialog.dismiss()
            getDerivativeData(items[item].toString())
        }
    }

    private fun initRec() {
        adapter = DerivativeAdapter(this)
        binder.recDerivative.adapter = adapter
        binder.recDerivative.addItemDecoration(
            DividerItemDecoration(
                App.context,
                LinearLayoutManager.VERTICAL
            )
        )
        getDerivativeData(AppUtils.getValue(AppConstant.DERIVATIVE_TYPE, requireActivity()))
    }

    private fun getDerivativeData(include_tickers: String) {
        AppUtils.showRequestDialog(requireActivity())
        utilsLight.derivativeData(include_tickers, object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {

                if (binder.swipeDerivative.isRefreshing) {
                    binder.swipeDerivative.isRefreshing = false
                    AppConstant.showToast("Refreshed !!")
                }

                AppUtils.hideDialog()
                val derivativeModel = obj as List<DerivativeModel>
                adapter.addItems(derivativeModel)
                Log.d(TAG, "Added: $derivativeModel")

            }

            override fun onFailed(msg: String?) {
                AppUtils.hideDialog()
                Log.d(TAG, "onFailed: $msg")
                AppConstant.showToast("try again !! ")
            }

        })
    }

    override fun onItemClick(obj: Any) {
        Log.d(TAG, "onItemClick: $obj")
    }

}