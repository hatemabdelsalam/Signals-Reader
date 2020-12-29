package com.hatem.digissquared.signal.model

import com.prostacare.doctoronline.network.RetrofitBuilder

class SignalRepository {

    val retrofitClient = RetrofitBuilder().instant

    suspend fun signals() = retrofitClient.signals()

}