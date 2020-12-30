package com.hatem.digissquared.signal.model

import android.content.Context
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import com.hatem.digissquared.R

data class SignalsModel(
    @SerializedName("RSRP")
    val rsrp: Float,
    @SerializedName("RSRQ")
    val rsrq: Float,
    @SerializedName("SINR")
    val sinr: Float
) {
    fun getRsrpColor(context: Context): Int {
        return when {
            rsrp < -110.0 -> {
                context.resources.getColor(R.color.rsrp_black, null)
            }
            rsrp in -110.0..100.0 -> {
                context.resources.getColor(R.color.rsrp_red, null)
            }
            rsrp in -100.0..-90.0 -> {
                context.resources.getColor(R.color.rsrp_yellow, null)
            }
            rsrp in -90.0..80.0 -> {
                context.resources.getColor(R.color.rsrp_green, null)
            }
            rsrp in -80.0..-70.0 -> {
                context.resources.getColor(R.color.rsrp_dark_green, null)
            }
            rsrp in -70.0..-60.0 -> {
                context.resources.getColor(R.color.rsrp_light_blue, null)
            }
            rsrp > -60.0 -> {
                context.resources.getColor(R.color.rsrp_blue, null)
            }
            else -> {
                context.resources.getColor(R.color.rsrp_blue, null)
            }
        }
    }

    fun getRsrqColor(context: Context): Int {
        return when {
            rsrq < -19.5 -> {
                context.resources.getColor(R.color.rsrq_black, null)
            }
            rsrq in -19.5..-14.0 -> {
                context.resources.getColor(R.color.rsrq_red, null)
            }
            rsrq in -14.0..-9.0 -> {
                context.resources.getColor(R.color.rsrq_yellow, null)
            }
            rsrq in -9.0..-3.0 -> {
                context.resources.getColor(R.color.rsrq_green, null)
            }
            rsrq > -3.0 -> {
                context.resources.getColor(R.color.rsrq_dark_green, null)
            }
            else -> {
                context.resources.getColor(R.color.rsrq_dark_green, null)
            }
        }
    }

    fun getSinrColor(context: Context): Int {
        return when{
            sinr < 0 -> { context.resources.getColor(R.color.sinr_black, null) }
            sinr in 0.0..5.0 -> { context.resources.getColor(R.color.sinr_red, null) }
            sinr in 5.0..10.0 -> { context.resources.getColor(R.color.sinr_orange, null) }
            sinr in 10.0..15.0 -> { context.resources.getColor(R.color.sinr_yellow, null) }
            sinr in 15.0..20.0 -> { context.resources.getColor(R.color.sinr_green, null) }
            sinr in 20.0..25.0 -> { context.resources.getColor(R.color.sinr_dark_green, null) }
            sinr in 25.0..30.0 -> { context.resources.getColor(R.color.sinr_light_blue, null) }
            sinr > 30 -> { context.resources.getColor(R.color.sinr_blue, null) }
            else -> { context.resources.getColor(R.color.sinr_blue, null) }
        }
    }

    fun getRsrpPercentage():Int {
       return  (((rsrp - (-140))/((-60) - -140))*100).toInt()
//        no-start\ (end-start)
    }

    fun getRsrqPercentage():Int {
        return (((rsrq- (-30))/(0 - -30))*100).toInt()
    }

    fun getSinrPercentage():Int {
        return (((sinr- (-10))/(30- -10))*100).toInt()
    }

}