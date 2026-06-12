package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uas_pemrogramanmobile_kelompok3.data.repository.AuthRepository
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ActivityPortalMasukBinding

class PortalMasukActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortalMasukBinding
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Task 1.3 (Max): Auto-login check
        if (authRepository.getCurrentUser() != null) {
            navigateToDashboard()
            return
        }

        binding = ActivityPortalMasukBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.cardHR.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.cardParticipant.setOnClickListener {
            // Participant login logic will be in Sprint 2
        }
    }

    private fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}