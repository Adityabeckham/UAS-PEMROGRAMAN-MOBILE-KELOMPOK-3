package com.example.uas_pemrogramanmobile_kelompok3.data.model

data class Question(
    val id: String = "",
    val text: String = "",
    val options: List<String> = emptyList(),
    val correctAnswerIndex: Int = -1
)