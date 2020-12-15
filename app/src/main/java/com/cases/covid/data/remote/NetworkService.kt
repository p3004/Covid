package com.cases.covid.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Pallab Banerjee on 12/15/2020.
 */
interface NetworkService {

    @GET(Endpoints.COUNTRIES)
    suspend fun getCovidDetailsByCountryName(
        @Path("countryName") countryName : String
    )

}