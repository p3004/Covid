package com.cases.covid.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cases.covid.data.model.CountryData
import com.cases.covid.data.repository.CountryDataRepository
import com.cases.covid.utils.common.Resource
import com.cases.covid.utils.network.NetworkHelper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Pallab Banerjee on 12/26/2020.
 */
class MainViewModel(
    private var networkHelper: NetworkHelper,
    private var countryDataRepository: CountryDataRepository

) : ViewModel() {

    private val _countryDataLiveData: MutableLiveData<Resource<CountryData>> = MutableLiveData()
    val countryDataLiveData: LiveData<Resource<CountryData>> = _countryDataLiveData

    fun getCountryData(countryName: String) {

        viewModelScope.launch {

            if (networkHelper.checkIsNetworkConnected()) {
                _countryDataLiveData.postValue(Resource.loading())
                try {
                    // coroutineScope is needed, else in case of any network error, it will crash
                    coroutineScope {
                        val countryData = countryDataRepository.fetchCountryDetails(countryName)
                        _countryDataLiveData.postValue(Resource.success(countryData))
                    }
                } catch (e: Exception) {
                    _countryDataLiveData.postValue(Resource.error(null, e.message))
                }


            }
        }


    }


}