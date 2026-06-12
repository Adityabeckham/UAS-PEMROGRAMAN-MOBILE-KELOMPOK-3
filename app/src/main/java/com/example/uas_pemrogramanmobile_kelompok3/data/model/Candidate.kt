package com.example.uas_pemrogramanmobile_kelompok3.data.model

data class Candidate(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val position: String = "",
    val token: String = "",
    val status: String = "Active", // Active, Completed, Pending
    val progress: Int = 0,
    val answers: Map<String, Int> = emptyMap(), // Key: QuestionId, Value: SelectedOptionIndex
    val createdAt: Long = System.currentTimeMillis()
)