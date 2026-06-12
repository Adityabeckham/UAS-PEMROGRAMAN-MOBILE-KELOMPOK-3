package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas_pemrogramanmobile_kelompok3.data.model.Candidate
import com.example.uas_pemrogramanmobile_kelompok3.data.repository.AuthRepository
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val authRepository = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupListeners()
    }

    private fun setupRecyclerView() {
        val dummyCandidates = listOf(
            Candidate("1", "Aditya", "Android Developer", "Completed", 100),
            Candidate("2", "Anisa", "UI Designer", "Active", 65),
            Candidate("3", "Max", "QA Engineer", "Active", 20)
        )

        binding.rvCandidates.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = CandidateAdapter(dummyCandidates)
            setHasFixedSize(true)
        }
    }

    private fun setupListeners() {
        binding.btnLogout.setOnClickListener {
            authRepository.logout()
            startActivity(Intent(this, PortalMasukActivity::class.java))
            finish()
        }
    }
}