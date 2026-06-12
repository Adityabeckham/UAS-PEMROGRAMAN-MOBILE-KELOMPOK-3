package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ActivityPortalMasukBinding

class PortalMasukActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortalMasukBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            // Participant login with token (Sprint 2 focus, but entry point is here)
            // Toast.makeText(this, "Peserta login via Token", Toast.LENGTH_SHORT).show()
        }
    }
}