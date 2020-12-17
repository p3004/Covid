package com.cases.covid.di.component

import com.cases.covid.CovidApplication
import com.cases.covid.data.repository.CountryDataRespository
import com.cases.covid.di.module.ApplicationModule
import com.cases.covid.utils.network.NetworkHelper
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 12/17/2020.
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(covidApplication: CovidApplication)

    fun getNetworkHelper() : NetworkHelper

    fun getCountryDataRepository() : CountryDataRespository

}