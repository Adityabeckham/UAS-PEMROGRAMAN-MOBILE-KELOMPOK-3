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

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    fun login(email: String, password: String) {
        var isValid = true

        if (email.isBlank()) {
            _emailError.value = "Email tidak boleh kosong"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = "Format email tidak valid"
            isValid = false
        } else {
            _emailError.value = null
        }

        if (password.isBlank()) {
            _passwordError.value = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 6) {
            _passwordError.value = "Password minimal 6 karakter"
            isValid = false
        } else {
            _passwordError.value = null
        }

        if (!isValid) return

        _isLoading.value = true
        repository.loginHR(email, password) { success, message ->
            _isLoading.value = false
            _loginResult.value = Pair(success, message)
        }
    }
}