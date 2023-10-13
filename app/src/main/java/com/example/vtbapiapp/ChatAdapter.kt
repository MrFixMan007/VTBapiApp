package com.example.vtbapiapp

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources.Theme
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vtbapiapp.databinding.ChatItemBinding
class ChatAdapter(val listener: ChatAdapter.Listener, context: Context) : RecyclerView.Adapter<ChatAdapter.ChatHolder>() {
    val chatList = ArrayList<ChatMessage>()
    val context: Context = context
    class ChatHolder(item: View, context: Context) : RecyclerView.ViewHolder(item) {
        val res = context.resources
        val binding = ChatItemBinding.bind(item)
        fun bind(chatMessage: ChatMessage, listener: Listener) = with(binding){
            if(!chatMessage.isRequest){
                chatLinearLayout.gravity = Gravity.LEFT
                chatTextView.backgroundTintList = ColorStateList.valueOf(res.getColor(R.color.secondaryElementsColor))
            }
            else{
                chatLinearLayout.gravity = Gravity.RIGHT
                chatTextView.backgroundTintList = ColorStateList.valueOf(res.getColor(R.color.specialActionElementsColor))
            }

            chatTextView.text = chatMessage.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ChatAdapter.ChatHolder(view, context)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ChatAdapter.ChatHolder, position: Int) {
        holder.bind(chatList[position], listener)
    }

    fun addChatItem(chatMessage: ChatMessage){
        chatList.add(chatMessage)
        notifyDataSetChanged()
    }

    fun clearList(){
        chatList.clear()
        notifyDataSetChanged()
    }

    interface Listener{
    }
}