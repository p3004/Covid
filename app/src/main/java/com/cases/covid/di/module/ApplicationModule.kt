package com.cases.covid.di.module

import android.content.Context
import com.cases.covid.CovidApplication
import com.cases.covid.data.remote.NetworkService
import com.cases.covid.data.remote.Networking
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 12/15/2020.
 */
@Module
class ApplicationModule(private val  application: CovidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext() : Context = application.applicationContext


    @Provides
    @Singleton
    fun provideNetworkService() : NetworkService = Networking.create()

}