package com.cases.covid.utils.common

import com.cases.covid.data.model.CountryData

class Helper {

    companion object{
        fun getIncomingMessage(countryData : CountryData) : String{
            return " Active: ${countryData.active} \n Today: ${countryData.todayCases} \n Today Deaths: ${countryData.todayDeaths} \n Recovered: ${countryData.recovered} \n Total Tests: ${countryData.totalTests}"
        }
    }
}