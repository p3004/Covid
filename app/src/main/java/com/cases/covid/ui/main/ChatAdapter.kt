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
import com.cases.covid.databinding.IncomingChatItemBinding
import com.cases.covid.databinding.OutgoingChatItemBinding
import com.cases.covid.utils.common.VIEW_TYPE_INCOMING_MESSAGE
import kotlinx.android.synthetic.main.incoming_chat_item.view.tvIncomingText
import kotlinx.android.synthetic.main.outgoing_chat_item.view.*

/**
 * Created by Pallab Banerjee on 12/30/2020.
 */

class ChatAdapter : ListAdapter<Chat,RecyclerView.ViewHolder>(ChatDiffCallBack()) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    class ChatInViewHolder(private val incomingChatItemBinding: IncomingChatItemBinding) : RecyclerView.ViewHolder(incomingChatItemBinding.root) {
        fun bind(chat: Chat) {
            incomingChatItemBinding.chatIncoming = chat
            incomingChatItemBinding.executePendingBindings()
        }
    }

    class ChatOutViewHolder(private val outgoingChatItemBinding: OutgoingChatItemBinding) : RecyclerView.ViewHolder(outgoingChatItemBinding.root) {
        fun bind(chat: Chat) {
            outgoingChatItemBinding.chatOutGoing = chat
            outgoingChatItemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         if(viewType == VIEW_TYPE_INCOMING_MESSAGE){
             val incomingChatItemBinding: IncomingChatItemBinding = IncomingChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return  ChatInViewHolder(incomingChatItemBinding)
        }
        val outgoingChatItemBinding : OutgoingChatItemBinding = OutgoingChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatOutViewHolder(outgoingChatItemBinding)
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