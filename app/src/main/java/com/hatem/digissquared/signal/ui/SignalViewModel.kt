package com.hatem.digissquared.signal.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebling.lolya.network.NetworkResponse
import com.hatem.digissquared.signal.model.SignalRepository
import com.hatem.digissquared.signal.model.SignalsModel
import kotlinx.coroutines.launch

class SignalViewModel : ViewModel() {

    private val signalRepository = SignalRepository()
    val signalsResult = MutableLiveData<SignalsModel>()

    fun signals() {
        viewModelScope.launch {

            val signals = signalRepository.signals()

            when (signals) {
                is NetworkResponse.Success -> signalsResult.value = signals.body
                is NetworkResponse.ApiError -> {
                }
                is NetworkResponse.NetworkError -> {
                }
                is NetworkResponse.UnknownError -> {
                }
            }

        }
    }
}