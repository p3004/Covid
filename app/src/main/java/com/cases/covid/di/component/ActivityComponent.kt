package com.cases.covid.di.component

import com.cases.covid.di.ActivityScope
import com.cases.covid.di.module.ActivityModule
import com.cases.covid.ui.main.MainActivity
import dagger.Component

/**
 * Created by Pallab Banerjee on 12/29/2020.
 */

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [ApplicationComponent::class]
)
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}