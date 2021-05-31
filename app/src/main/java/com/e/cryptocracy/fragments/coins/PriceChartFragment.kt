package com.e.cryptocracy.fragments.coins

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.cryptocracy.R
import com.e.cryptocracy.databinding.FragmentPriceChartBinding
import com.e.cryptocracy.interfaces.ApiCallbackInterface
import com.e.cryptocracy.model.CoinModel
import com.e.cryptocracy.utils.APIUtils
import com.e.cryptocracy.utils.AppConstant
import com.e.cryptocracy.utils.AppUtils
import com.highsoft.highcharts.common.hichartsclasses.*
import java.util.*


class PriceChartFragment(var id: String, var price: Double) : Fragment() {
    private val TAG = "PriceChartFragment"

    lateinit var binding: FragmentPriceChartBinding

    //graph Variables
    var options: HIOptions? = null
    var plotOptions: HIPlotOptions? = null
    var hiExporting: HIExporting? = null

    var days = "1"
    val apiUtils = APIUtils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPriceChartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initGraph()
        getCoinData(id)
    }

    private fun getCoinData(id: String) {
        AppUtils.showRequestDialog(requireActivity())

        apiUtils.getCoinDataById(id, object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {

                AppUtils.hideDialog()
                binding.textView20.text = AppUtils.getCurrencyFormat(price)
                val coins: CoinModel = obj as CoinModel
                Log.d(TAG, "onSuccess: $price")
                coins.current_price = price
                binding.coin = coins



                setGraph(coins)
                binding.tvCurrency.text = AppUtils.getValue(AppConstant.CURRENCY, requireActivity())
            }

            override fun onFailed(msg: String) {
                AppConstant.showToast(getString(R.string.try_again))
                AppUtils.hideDialog()
            }

        })
    }

    private fun initGraph() {
        binding.chartView.theme = "sand-signika"
        options = HIOptions()
        val yAxis = HIYAxis()
        yAxis.title = HITitle()
        yAxis.title.text = binding.tvCurrency.text.toString()
        options!!.yAxis = object : ArrayList<HIYAxis?>() {
            init {
                add(yAxis)
            }
        }
        val hiTitle = HITitle()
        hiTitle.text = ""
        options!!.title = hiTitle
        plotOptions = HIPlotOptions()
        plotOptions!!.line = HILine()
        plotOptions!!.line.enableMouseTracking = true
        options!!.plotOptions = plotOptions
        hiExporting = HIExporting()
        hiExporting!!.enabled = false
        options!!.exporting = hiExporting
        binding.chartView.options = options
    }

    private fun setGraph(coins: CoinModel) {
        apiUtils.get_OHLC_Data(id, days, object : ApiCallbackInterface {
            override fun onSuccess(obj: Any) {
                val data: ArrayList<ArrayList<String>> = obj as ArrayList<ArrayList<String>>
                Log.d(TAG, "onSuccess: " + data[0][0])


                val xAxis = HIXAxis()
                val categoriesList = arrayOfNulls<String>(data.size)
                for (a in data.indices) {
                    categoriesList[a] = data[a][0]
                }
                xAxis.categories = ArrayList(Arrays.asList(*categoriesList))
                options!!.xAxis = object : java.util.ArrayList<HIXAxis?>() {
                    init {
                        add(xAxis)
                    }
                }


                val series1 = HISeries()
                series1.name = coins.name
                val sysData = arrayOfNulls<Number>(data.size)
                for (a in data.indices) {
                    sysData[a] = data[a][1].toDouble()
                }
                series1.data = ArrayList(Arrays.asList(*sysData))
                options!!.series = ArrayList(Arrays.asList(series1))



                binding.chartView.redraw()
                binding.progressBar4.visibility = View.GONE

            }

            override fun onFailed(msg: String) {
                Log.d(TAG, "onFailed: $msg")
            }

        })

    }
}