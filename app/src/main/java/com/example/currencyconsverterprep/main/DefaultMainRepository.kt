package com.example.currencyconsverterprep.main

import com.example.currencyconsverterprep.data.CurrencyApi
import com.example.currencyconsverterprep.data.models.CurrencyResponse
import com.example.currencyconsverterprep.util.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(

    private val api : CurrencyApi


) : MainRepository{

    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRetes(base)
            val result = response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error(response.message())
            }


        }catch (e: Exception){
            Resource.Error(e.message ?: "에러..")
        }
    }
}