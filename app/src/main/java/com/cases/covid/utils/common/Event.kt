package com.cases.covid.utils.common

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Pallab Banerjee on 11/23/2020.
 */
/**
 * Used as a wrapper for data that is exposed via LiveData that represents an event.
 **/
data class Event<out T>(private val content : T) {


    private var hasBeenHandled = AtomicBoolean(false)

    /**
     * Returns the content and prevents its use again.
     * */
    fun getIfNotHandled() : T? = if(hasBeenHandled.getAndSet(true)) null else content


    /**
     * Returns the content even if its been already handled.
     * */
    fun peek() : T = content


}