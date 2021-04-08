package com.cases.covid.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cases.covid.R
import com.cases.covid.data.model.Chat
import kotlinx.android.synthetic.main.incoming_chat_item.view.tvIncomingText
import kotlinx.android.synthetic.main.outgoing_chat_item.view.*

/**
 * Created by Pallab Banerjee on 12/30/2020.
 */

class ChatAdapter : ListAdapter<Chat,RecyclerView.ViewHolder>(ChatDiffCallBack()) {

    companion object{
         const val VIEW_TYPE_INCOMING_MESSAGE = 1;
         const val VIEW_TYPE_OUTGOING_MESSAGE = 2;
    }


    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }




    class ChatInViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvIncomingText: TextView = itemView.tvIncomingText

        fun bind(chat: Chat) {
              tvIncomingText.text = chat.message
        }

    }

    class ChatOutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvOutGoingChat: TextView = itemView.tvOutGoingChat

        fun bind(chat: Chat) {
            tvOutGoingChat.text = chat.message
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         if(viewType == VIEW_TYPE_INCOMING_MESSAGE){
             return  ChatInViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.incoming_chat_item,parent,false))
        }
        return ChatOutViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.incoming_chat_item,parent,false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItem(position).viewType == VIEW_TYPE_INCOMING_MESSAGE){
            (holder as ChatInViewHolder).bind(getItem(position))
        }
        else{
            (holder as ChatOutViewHolder).bind(getItem(position))
        }
    }


}


private class ChatDiffCallBack : DiffUtil.ItemCallback<Chat>(){
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean  = oldItem.message == newItem.message

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem == newItem

}