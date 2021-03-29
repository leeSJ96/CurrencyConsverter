package com.example.currencyconsverterprep.data

import com.example.currencyconsverterprep.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    //베이스
    @GET("/latest")
    suspend fun getRetes(
        @Query("base") base : String
    ) : Response<CurrencyResponse>
}