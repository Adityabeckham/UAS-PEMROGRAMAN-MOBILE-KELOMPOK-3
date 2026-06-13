package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.uas_pemrogramanmobile_kelompok3.R
import com.example.uas_pemrogramanmobile_kelompok3.data.repository.AuthRepository
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ActivityPortalMasukBinding
import com.example.uas_pemrogramanmobile_kelompok3.ui.viewmodel.CandidateViewModel

class PortalMasukActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortalMasukBinding
    private lateinit var viewModel: CandidateViewModel
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        if (authRepository.getCurrentUser() != null) {
            navigateToDashboard()
            return
        }

        binding = ActivityPortalMasukBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this)[CandidateViewModel::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupValidation()
        setupListeners()
        observeViewModel()
    }

    private fun setupValidation() {
        binding.btnSubmitToken.isEnabled = false

        binding.etToken.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val token = s.toString().trim()
                binding.btnSubmitToken.isEnabled = token.length == 6
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupListeners() {
        binding.cardHR.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.cardParticipant.setOnClickListener {
            binding.choiceLayout.visibility = View.GONE
            binding.paneCandidateLayout.visibility = View.VISIBLE
            binding.etToken.text?.clear()
            binding.etToken.requestFocus()
        }

        binding.btnBackToken.setOnClickListener {
            binding.paneCandidateLayout.visibility = View.GONE
            binding.choiceLayout.visibility = View.VISIBLE
        }

        binding.btnSubmitToken.setOnClickListener {
            val token = binding.etToken.text.toString().trim().uppercase()
            if (token.isNotEmpty()) {
                viewModel.loginParticipant(token)
            }
        }
        
        binding.tvForgotToken.setOnClickListener {
            Toast.makeText(this, "Silakan hubungi admin ujian kelompok 3 untuk mendapatkan token Anda.", Toast.LENGTH_LONG).show()
        }
    }

    private fun observeViewModel() {
        viewModel.participantLoginResult.observe(this) { result ->
            result.onSuccess { candidate ->
                if (candidate != null) {
                    if (candidate.status == "Completed") {
                        Toast.makeText(this, "Ujian sudah selesai untuk token ini.", Toast.LENGTH_LONG).show()
                    } else {
                        val intent = Intent(this, ExamActivity::class.java).apply {
                            putExtra("CANDIDATE_ID", candidate.id)
                        }
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, getString(R.string.error_invalid_token), Toast.LENGTH_SHORT).show()
                }
            }.onFailure { exception ->
                Toast.makeText(this, "Gagal terhubung ke server: ${exception.message}", Toast.LENGTH_LONG).show()
            }
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}