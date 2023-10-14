package com.example.vtbapiapp.database.dtos.gpt

import com.google.gson.annotations.SerializedName

data class QuestionDto (
    @SerializedName("question")
    val question: String? = null,
    @SerializedName("chat_id")
    val chat_id: String? = null,
    @SerializedName("timestamp")
    val timestamp: Long? = null,
    @SerializedName("token")
    val token: String? = null
)
