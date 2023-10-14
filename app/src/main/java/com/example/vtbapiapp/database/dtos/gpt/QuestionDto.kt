package com.example.vtbapiapp.database.dtos.gpt

data class QuestionDto (
    val question: String? = null,
    val chat_id: String? = null,
    val timestamp: Long? = null,
    val token: String? = null
)
