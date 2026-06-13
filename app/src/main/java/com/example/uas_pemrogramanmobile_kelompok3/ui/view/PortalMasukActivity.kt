package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.cardHR.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.cardParticipant.setOnClickListener {
            showTokenLoginDialog()
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
                    // Berarti query sukses tapi token tidak ditemukan di database
                    Toast.makeText(this, getString(R.string.error_invalid_token), Toast.LENGTH_SHORT).show()
                }
            }.onFailure { exception ->
                // Ini biasanya karena masalah koneksi atau Firestore Rules belum di-update
                Toast.makeText(this, "Gagal terhubung ke server: ${exception.message}", Toast.LENGTH_LONG).show()
            }
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showTokenLoginDialog() {
        val input = EditText(this)
        input.hint = getString(R.string.hint_token)
        input.setPadding(64, 48, 64, 48)

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_token_title))
            .setView(input)
            .setPositiveButton(getString(R.string.btn_start_exam)) { _, _ ->
                val token = input.text.toString().trim().uppercase()
                if (token.isNotEmpty()) {
                    viewModel.loginParticipant(token)
                }
            }
            .setNegativeButton(getString(R.string.btn_cancel), null)
            .show()
    }

    private fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}