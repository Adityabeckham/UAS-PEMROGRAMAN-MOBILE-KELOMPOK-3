package com.example.uas_pemrogramanmobile_kelompok3.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uas_pemrogramanmobile_kelompok3.data.repository.AuthRepository

class LoginViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {

    private val _loginResult = MutableLiveData<Pair<Boolean, String?>>()
    val loginResult: LiveData<Pair<Boolean, String?>> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Task 1.3 (Max): Using null for no error, and specific keys for validation
    private val _emailErrorKey = MutableLiveData<String?>()
    val emailErrorKey: LiveData<String?> = _emailErrorKey

    private val _passwordErrorKey = MutableLiveData<String?>()
    val passwordErrorKey: LiveData<String?> = _passwordErrorKey

    fun login(email: String, password: String) {
        var isValid = true

        if (email.isBlank()) {
            _emailErrorKey.value = "EMPTY"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailErrorKey.value = "INVALID"
            isValid = false
        } else {
            _emailErrorKey.value = null
        }

        if (password.isBlank()) {
            _passwordErrorKey.value = "EMPTY"
            isValid = false
        } else if (password.length < 6) {
            _passwordErrorKey.value = "SHORT"
            isValid = false
        } else {
            _passwordErrorKey.value = null
        }

        if (!isValid) return

        _isLoading.value = true
        repository.loginHR(email, password) { success, message ->
            _isLoading.value = false
            _loginResult.value = Pair(success, message)
        }
    }
}