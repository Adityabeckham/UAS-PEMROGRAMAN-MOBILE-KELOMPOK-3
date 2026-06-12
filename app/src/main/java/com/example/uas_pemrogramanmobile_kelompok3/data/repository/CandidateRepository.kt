package com.example.uas_pemrogramanmobile_kelompok3.data.repository

import com.example.uas_pemrogramanmobile_kelompok3.data.model.Candidate
import com.example.uas_pemrogramanmobile_kelompok3.data.model.Report
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class CandidateRepository(private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()) {

    private val candidateCollection = firestore.collection("participants")
    private val reportCollection = firestore.collection("reports")

    suspend fun addCandidate(candidate: Candidate): Result<Unit> {
        return try {
            candidateCollection.document(candidate.id).set(candidate).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

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

    // Task 4.1 (Anisa): Get reports in real-time
    fun getReportsRealTime(): Flow<List<Report>> = callbackFlow {
        val subscription = reportCollection.orderBy("completedAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val reports = snapshot.toObjects(Report::class.java)
                    trySend(reports)
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