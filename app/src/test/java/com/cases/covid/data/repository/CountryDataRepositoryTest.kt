package com.cases.covid.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cases.covid.data.model.CountryData
import com.cases.covid.data.remote.NetworkService
import com.cases.covid.utils.rule.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Pallab Banerjee on 12/17/2020.
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountryDataRepositoryTest {

    @get : Rule
    val testInstantExecutorRule : TestRule = InstantTaskExecutorRule()

    @get : Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var networkService : NetworkService

    private lateinit var countryDataRepository : CountryDataRespository

    private lateinit var countryData: CountryData


    @Before
    fun setUp(){
        countryDataRepository = CountryDataRespository(networkService)
        countryData = CountryData(0,0,0,
            "",0,0,0,0,
            0,0,0,0)
    }

    @Test
    fun whenFetchCountryData_shouldCallFetchCountryDetails(){

       runBlocking {

            doReturn(countryData)
                .`when`(networkService)
                .getCovidDetailsByCountryName("")

            countryDataRepository.fetchCountryDetails("")

            verify(networkService).getCovidDetailsByCountryName("")

        }

    }

}