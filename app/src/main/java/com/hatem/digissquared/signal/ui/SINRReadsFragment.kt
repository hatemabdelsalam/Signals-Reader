package com.hatem.digissquared.signal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.hatem.digissquared.R
import kotlinx.android.synthetic.main.fragment_chart.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SINRReadsFragment : Fragment() {

    lateinit var signalViewModel: SignalViewModel
    var sinrList=ArrayList<Float>()

    var xAxisList = ArrayList<String>()
    var yValues = ArrayList<Entry>()
    lateinit var dataSet: LineDataSet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signalViewModel = ViewModelProvider(requireActivity()).get(SignalViewModel::class.java)

        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChart()
        initObservers()
    }

    fun initObservers() {
        signalViewModel.signalsResult.observe(viewLifecycleOwner, Observer {
            sinrList.add(it.sinr)
            xAxisList.add(getTime())

            yValues.add(Entry(xAxisList.size.toFloat(), it.sinr))

            getDataSet()
        })
    }

    fun initChart() {

        lineChart.setDrawGridBackground(true)
        lineChart.invalidate()

        val leftAxis: YAxis = lineChart.getAxisLeft()
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.textColor = resources.getColor(R.color.rsrp_light_blue, null)
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = -10f
        leftAxis.axisMaximum = 30f

        lineChart.getAxisRight().isEnabled = false

        getDataSet()
    }
    fun initXAxis() {
        val xAxis: XAxis = lineChart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textColor = resources.getColor(R.color.rsrp_light_blue, null)
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisList)
    }

    private fun getTime(): String {
        val dateFormat: DateFormat = SimpleDateFormat("HH:mm:ss")
        val date = Date()
        return dateFormat.format(date)
    }

    fun getDataSet() {

        dataSet = LineDataSet(yValues, resources.getString(R.string.rsrp))

        dataSet.setLineWidth(2f)
        dataSet.setCircleRadius(6f)
        dataSet.setFillAlpha(65)
        dataSet.setFillColor(ColorTemplate.getHoloBlue())

        lineChart.data = LineData(dataSet)

        initXAxis()

        dataSet.notifyDataSetChanged()
        lineChart.notifyDataSetChanged()

        lineChart.invalidate()

    }

}