package com.cases.covid.utils.common

/**
 * Created by Pallab Banerjee on 7/25/2020.
 */

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents a resource
 * */
data class Resource<out T>  private constructor (val status: Status, val data:T?, val message : String?) {


    companion object{


        fun <T> success(data:T? = null):Resource<T> = Resource(Status.SUCCESS,data,null)

        fun <T> error(data:T? = null, message : String? = null): Resource<T> = Resource(Status.ERROR,data,message)

        fun <T> loading(data:T? = null): Resource<T> = Resource(Status.LOADING,data,null)

        fun <T> unknown(data:T? = null): Resource<T> = Resource(Status.UNKNOWN,data,null)
    }



}