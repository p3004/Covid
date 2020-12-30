package com.cases.covid.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cases.covid.data.repository.CountryDataRepository
import com.cases.covid.di.ActivityScope
import com.cases.covid.ui.main.MainViewModel
import com.cases.covid.utils.ViewModelProviderFactory
import com.cases.covid.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides

/**
 * Created by Pallab Banerjee on 12/29/2020.
 */

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideMainViewModel(
        networkHelper: NetworkHelper,
        countryDataRepository: CountryDataRepository
    ): MainViewModel = ViewModelProvider(activity, ViewModelProviderFactory(MainViewModel::class) {
        MainViewModel(networkHelper, countryDataRepository)
    }).get(MainViewModel::class.java)


}