package com.example.currencyconsverterprep.main

import com.example.currencyconsverterprep.data.models.CurrencyResponse
import com.example.currencyconsverterprep.util.Resource

interface MainRepository {

    suspend fun getRates(base:String): Resource<CurrencyResponse>


}