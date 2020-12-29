package com.prostacare.doctoronline.network

import com.ebling.lolya.network.NetworkResponse
import com.hatem.digissquared.signal.model.SignalsModel
import retrofit2.http.GET

interface Apis {

    @GET(Urls.SIGNALS)
    suspend fun signals(): NetworkResponse<SignalsModel, Error>

}