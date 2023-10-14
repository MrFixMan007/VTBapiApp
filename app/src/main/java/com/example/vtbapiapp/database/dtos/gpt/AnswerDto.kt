package com.example.vtbapiapp.database.dtos.gpt

import com.google.gson.annotations.SerializedName

data class AnswerDto (
    @SerializedName("chat_id")
    val chat_id: String? = null,
    @SerializedName("bot_response")
    val bot_response: String? = null,
    @SerializedName("timestamp")
    val timestamp: Long? = null
)
