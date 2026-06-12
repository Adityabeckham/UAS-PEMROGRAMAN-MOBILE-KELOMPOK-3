package com.example.uas_pemrogramanmobile_kelompok3.data.repository

import com.example.uas_pemrogramanmobile_kelompok3.data.model.Candidate
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CandidateRepository(private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()) {

    private val candidateCollection = firestore.collection("participants")

    suspend fun addCandidate(candidate: Candidate): Result<Unit> {
        return try {
            candidateCollection.document(candidate.id).set(candidate).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun generateUniqueToken(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..6)
            .map { chars.random() }
            .joinToString("")
    }
}