package com.example.uas_pemrogramanmobile_kelompok3.data.model

data class Report(
    val id: String = "",
    val candidateId: String = "",
    val candidateName: String = "",
    val position: String = "",
    val finalScore: Int = 0,
    val completedAt: Long = System.currentTimeMillis()
)