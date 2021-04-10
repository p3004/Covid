package com.cases.covid.data.model

import com.cases.covid.utils.common.VIEW_TYPE_INCOMING_MESSAGE
import com.cases.covid.utils.common.VIEW_TYPE_OUTGOING_MESSAGE

/**
 * Created by Pallab Banerjee on 2/2/2021.
 */
data class Chat(
val viewType : Int,
val message : String
){

    fun convertCountryDataToChat(countryData : CountryData) : Chat{
                return  Chat(
                    viewType = VIEW_TYPE_INCOMING_MESSAGE,
                    message = (
                            " Active: ${countryData.active} \n  Today: ${countryData.todayCases} \n Today Deaths: ${countryData.todayDeaths} \n Recovered: ${countryData.recovered} \n Total Tests: ${countryData.totalTests}")
                )
    }


    fun convertAskDataToChat(askData : String) : Chat {
        return Chat(
            viewType = VIEW_TYPE_OUTGOING_MESSAGE,
            message = askData
        )
    }

}