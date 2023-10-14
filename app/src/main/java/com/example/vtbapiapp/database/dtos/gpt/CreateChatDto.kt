package com.example.vtbapiapp.database.dtos.gpt

import com.google.gson.annotations.SerializedName

data class CreateChatDto (
    @SerializedName("user_id")
    val user_id:String)