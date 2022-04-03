package com.cases.covid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cases.covid.CovidApplication
import com.cases.covid.R
import com.cases.covid.data.model.Chat
import com.cases.covid.data.model.CountryData
import com.cases.covid.databinding.ActivityMainBinding
import com.cases.covid.di.component.DaggerActivityComponent
import com.cases.covid.di.module.ActivityModule
import com.cases.covid.utils.common.Helper
import com.cases.covid.utils.common.Status
import com.cases.covid.utils.common.VIEW_TYPE_INCOMING_MESSAGE
import com.cases.covid.utils.common.VIEW_TYPE_OUTGOING_MESSAGE
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var chatAdapter: ChatAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private val chatList: MutableList<Chat> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?){
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setUpView()
    }

    private fun injectDependencies(){
        DaggerActivityComponent.builder()
            .applicationComponent((application as CovidApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    private fun setUpView(){
        rvChats.apply {
            adapter = chatAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun onResume(){
        super.onResume()
        btnAsk.setOnClickListener {
            val askData = etChatText.text.toString()
            mainViewModel.getCountryData(askData)
            chatList.add(Chat(
                viewType = VIEW_TYPE_OUTGOING_MESSAGE,
                message = askData
            ))
        }
        subscribeUi()
    }

    private fun subscribeUi(){
        mainViewModel.countryDataLiveData.observe(this, Observer {
            if (it.status == Status.SUCCESS){
                chatList.add(Chat(
                    viewType = VIEW_TYPE_INCOMING_MESSAGE,
                    message = Helper.getIncomingMessage(it.data!!)
                ))
            }
        })
        chatAdapter.submitList(chatList)
    }
}