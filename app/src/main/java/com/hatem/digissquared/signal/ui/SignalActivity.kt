package com.hatem.digissquared.signal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hatem.digissquared.R
import com.hatem.digissquared.databinding.ActivitySignalBinding
import com.hatem.digissquared.signal.adapter.TabsPagerAdapter
import java.util.*


class SignalActivity : AppCompatActivity() {

    lateinit var signalViewModel: SignalViewModel
    lateinit var binding: ActivitySignalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signal)
        signalViewModel = ViewModelProvider(this).get(SignalViewModel::class.java)

        initSwipper()
        initObservers()
        signals()
    }

    fun initObservers() {
        signalViewModel.signalsResult.observe(this, Observer {
            binding.signals = it

        })
    }

    fun initSwipper() {

        val tabsPagerAdapter = TabsPagerAdapter(supportFragmentManager)

        tabsPagerAdapter.addFragment(
            RSRPReadsFragment(),
            getResources().getString(R.string.rsrp)
        )

        tabsPagerAdapter.addFragment(
            RSRQReadsFragment(),
            getResources().getString(R.string.rsrq)
        )

        tabsPagerAdapter.addFragment(
            SINRReadsFragment(),
            getResources().getString(R.string.sinr)
        )

        binding.viewPager.adapter = tabsPagerAdapter

    }

    fun signals() {

        Timer().schedule(object : TimerTask() {
            override fun run() {
                signalViewModel.signals()
            }
        }, 0, 2000)
    }
}