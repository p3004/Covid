package com.cases.covid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cases.covid.CovidApplication
import com.cases.covid.R
import com.cases.covid.di.component.DaggerActivityComponent
import com.cases.covid.di.module.ActivityModule
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    private fun injectDependencies(){
        DaggerActivityComponent.builder()
            .applicationComponent((application as CovidApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }


    private fun setUpView(){




    }

}