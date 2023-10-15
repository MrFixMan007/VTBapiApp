package com.example.vtbapiapp.database.dtos.gpt

import com.google.gson.annotations.SerializedName

data class ChatMessage(
    @SerializedName("answer") val answer: String
)