package com.cases.covid

import android.app.Application
import com.cases.covid.di.component.ApplicationComponent
import com.cases.covid.di.component.DaggerApplicationComponent
import com.cases.covid.di.module.ApplicationModule

/**
 * Created by Pallab Banerjee on 11/20/2020.
 */
class CovidApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        injectApplicationDependencies()
        super.onCreate()
    }


    private fun injectApplicationDependencies(){
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}
