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

    // Kunci Jawaban Dummy
    private val answerKeys = mapOf(
        "1" to 0, // APK -> Android Package Kit
        "2" to 1, // Language -> Kotlin
        "3" to 2, // UI Component -> Activity
        "4" to 0, // Manifest -> AndroidManifest.xml
        "5" to 1  // Layout -> LinearLayout
    )

    suspend fun addCandidate(candidate: Candidate): Result<Unit> {
        return try {
            candidateCollection.document(candidate.id).set(candidate).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Task 4.3 (Max): Function to verify token for participant login
    suspend fun findCandidateByToken(token: String): Result<Candidate?> {
        return try {
            val snapshot = candidateCollection.whereEqualTo("token", token).get().await()
            if (snapshot.isEmpty) {
                Result.success(null)
            } else {
                val candidate = snapshot.documents[0].toObject(Candidate::class.java)
                Result.success(candidate)
            }
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

    suspend fun completeExam(candidateId: String): Result<Int> {
        return try {
            val snapshot = candidateCollection.document(candidateId).get().await()
            val candidate = snapshot.toObject(Candidate::class.java) ?: throw Exception("Candidate not found")

            // Kalkulasi Skor
            var correctCount = 0
            candidate.answers.forEach { (qId, selectedIdx) ->
                if (answerKeys[qId] == selectedIdx) {
                    correctCount++
                }
            }
            
            val finalScore = (correctCount.toFloat() / answerKeys.size * 100).toInt()

            // Simpan ke Laporan
            val report = Report(
                id = candidateId,
                candidateId = candidateId,
                candidateName = candidate.name,
                position = candidate.position,
                finalScore = finalScore
            )
            
            reportCollection.document(report.id).set(report).await()
            
            // Update status kandidat
            candidateCollection.document(candidateId).update(
                "status", "Completed",
                "progress", 100
            ).await()

            Result.success(finalScore)
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