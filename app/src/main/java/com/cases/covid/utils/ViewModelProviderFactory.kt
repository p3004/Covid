package com.cases.covid.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Singleton
import kotlin.IllegalArgumentException
import kotlin.reflect.KClass

/**
 * Created by Pallab Banerjee on 12/15/2020.
 */

/**
* Used to create ViewModel instances with custom constructors
* */

@Singleton
class ViewModelProviderFactory<T : ViewModel>(

    private val KClass : KClass<T>,
    private val creator:() -> T

) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KClass.java)) return creator() as T
        throw IllegalArgumentException("Unknown class name")
    }

}