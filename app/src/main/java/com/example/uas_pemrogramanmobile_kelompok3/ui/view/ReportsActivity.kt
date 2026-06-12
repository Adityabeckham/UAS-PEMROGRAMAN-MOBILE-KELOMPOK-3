package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ActivityReportsBinding
import com.example.uas_pemrogramanmobile_kelompok3.ui.viewmodel.CandidateViewModel

class ReportsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportsBinding
    private lateinit var viewModel: CandidateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CandidateViewModel::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupToolbar()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun setupRecyclerView() {
        binding.rvReports.layoutManager = LinearLayoutManager(this)
        binding.rvReports.setHasFixedSize(true)
    }

    private fun observeViewModel() {
        // Task 4.1 (Anisa): Display reports in RecyclerView
        viewModel.reports.observe(this) { reports ->
            if (reports.isEmpty()) {
                binding.tvEmptyReports.visibility = View.VISIBLE
                binding.rvReports.visibility = View.GONE
            } else {
                binding.tvEmptyReports.visibility = View.GONE
                binding.rvReports.visibility = View.VISIBLE
                binding.rvReports.adapter = ReportAdapter(reports)
            }
        }
    }
}