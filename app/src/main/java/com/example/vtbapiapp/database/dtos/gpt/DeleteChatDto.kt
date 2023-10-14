package com.example.vtbapiapp.database.dtos.gpt

import com.google.gson.annotations.SerializedName

data class DeleteChatDto (
    @SerializedName("chat_id")
    val chat_id:String)