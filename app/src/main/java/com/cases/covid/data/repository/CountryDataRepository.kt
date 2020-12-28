package com.cases.covid.data.repository

import com.cases.covid.data.model.CountryData
import com.cases.covid.data.remote.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Pallab Banerjee on 12/15/2020.
 */
class CountryDataRepository @Inject constructor(private val networkService: NetworkService) {

        suspend fun fetchCountryDetails(countryName : String) : CountryData {
            return withContext(Dispatchers.IO){
                networkService.getCovidDetailsByCountryName(countryName)}
        }

}