package com.example.uas_pemrogramanmobile_kelompok3.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.uas_pemrogramanmobile_kelompok3.data.model.Candidate
import com.example.uas_pemrogramanmobile_kelompok3.data.repository.CandidateRepository
import kotlinx.coroutines.launch
import java.util.UUID

class CandidateViewModel(private val repository: CandidateRepository = CandidateRepository()) : ViewModel() {

    private val _addResult = MutableLiveData<Result<String>>()
    val addResult: LiveData<Result<String>> = _addResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Task 3.3 (Aditya): Real-time candidates for Dashboard
    val candidates: LiveData<List<Candidate>> = repository.getCandidatesRealTime().asLiveData()

    fun addCandidate(name: String, email: String, position: String) {
        viewModelScope.launch {
            _isLoading.value = true
            
            val token = repository.generateUniqueToken()
            val id = UUID.randomUUID().toString()
            
            val candidate = Candidate(
                id = id,
                name = name,
                email = email,
                position = position,
                token = token,
                status = "Active"
            )

            val result = repository.addCandidate(candidate)
            if (result.isSuccess) {
                _addResult.value = Result.success(token)
            } else {
                _addResult.value = Result.failure(result.exceptionOrNull() ?: Exception("Unknown error"))
            }
            _isLoading.value = false
        }
    }

    // Task 3.3 (Aditya): Sync answers in real-time
    fun syncAnswers(candidateId: String, answers: Map<String, Int>, progress: Int) {
        viewModelScope.launch {
            repository.updateAnswers(candidateId, answers, progress)
        }
    }
}