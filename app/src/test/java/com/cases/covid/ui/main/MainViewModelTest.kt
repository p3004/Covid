package com.cases.covid.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cases.covid.data.model.CountryData
import com.cases.covid.data.repository.CountryDataRepository
import com.cases.covid.utils.common.Resource
import com.cases.covid.utils.network.NetworkHelper
import com.cases.covid.utils.rule.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Pallab Banerjee on 12/28/2020.
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get : Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get : Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    lateinit var networkHelper: NetworkHelper

    @Mock
    lateinit var countryDataRepository: CountryDataRepository

    @Mock
    lateinit var countryDataTestObserver: Observer<Resource<CountryData>>

    private lateinit var mainViewModel: MainViewModel

    private lateinit var countryData: CountryData


    @Before
    fun setUp() {
        mainViewModel = MainViewModel(networkHelper, countryDataRepository)
        countryData = CountryData(
            0, 0, 0,
            "", 0, 0, 0, 0,
            0, 0, 0, 0
        )
    }


    @Test
    fun givenNoInternet_whenGetCountryDataCalled_shouldReturnNetworkError() {

        testCoroutineRule.runBlockingTest {
            val errorMessage = "No Network Error"

            doReturn(false)
                .`when`(networkHelper)
                .checkIsNetworkConnected()

            mainViewModel.countryDataLiveData.observeForever(countryDataTestObserver)

            mainViewModel.getCountryData("")

            verify(countryDataTestObserver).onChanged(Resource.error(null, "No Internet connection!"))

            mainViewModel.countryDataLiveData.removeObserver(countryDataTestObserver)
        }

    }


    @Test
    fun givenServerResponse200_whenGetCountryDataCalled_shouldReturnCountryData(){

        testCoroutineRule.runBlockingTest {

            doReturn(true)
                .`when`(networkHelper)
                .checkIsNetworkConnected()

            doReturn(countryData)
                .`when`(countryDataRepository)
                .fetchCountryDetails("")

            mainViewModel.countryDataLiveData.observeForever(countryDataTestObserver)

            mainViewModel.getCountryData("")

            verify(countryDataTestObserver).onChanged(Resource.success(countryData))

            mainViewModel.countryDataLiveData.removeObserver(countryDataTestObserver)

        }

    }


    @Test
    fun givenServerResponseError_whenGetCountryDataCalled_shouldReturnServerError(){

        testCoroutineRule.runBlockingTest {

            val errorMessage  = "This is a error message"

            doReturn(true)
                .`when`(networkHelper)
                .checkIsNetworkConnected()

            doThrow(RuntimeException(errorMessage))
                .`when`(countryDataRepository)
                .fetchCountryDetails("")

            mainViewModel.countryDataLiveData.observeForever(countryDataTestObserver)

            mainViewModel.getCountryData("")

            verify(countryDataTestObserver).onChanged(Resource.error(null, errorMessage))

            mainViewModel.countryDataLiveData.removeObserver(countryDataTestObserver)



        }


    }

}