package com.example.uas_pemrogramanmobile_kelompok3.data.model

data class Candidate(
    val id: String = "",
    val name: String = "",
    val position: String = "",
    val status: String = "Active", // Active, Completed, Pending
    val progress: Int = 0
)