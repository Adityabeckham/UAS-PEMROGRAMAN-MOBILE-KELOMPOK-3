package com.example.uas_pemrogramanmobile_kelompok3.data.repository

import com.example.uas_pemrogramanmobile_kelompok3.data.model.Candidate
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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

    // Task 3.3 (Aditya): Auto-save answers to Firestore
    suspend fun updateAnswers(candidateId: String, answers: Map<String, Int>, progress: Int): Result<Unit> {
        return try {
            candidateCollection.document(candidateId).update(
                "answers", answers,
                "progress", progress
            ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Task 3.3 (Aditya): Real-time sync for Dashboard
    fun getCandidatesRealTime(): Flow<List<Candidate>> = callbackFlow {
        val subscription = candidateCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val candidates = snapshot.toObjects(Candidate::class.java)
                trySend(candidates)
            }
        }
        awaitClose { subscription.remove() }
    }

    fun generateUniqueToken(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..6)
            .map { chars.random() }
            .joinToString("")
    }
}