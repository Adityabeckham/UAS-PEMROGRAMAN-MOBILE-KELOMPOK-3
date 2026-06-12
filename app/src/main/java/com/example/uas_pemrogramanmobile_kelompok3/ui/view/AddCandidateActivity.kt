package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ActivityAddCandidateBinding

class AddCandidateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCandidateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCandidateBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupToolbar()
        setupValidation()
        setupListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun setupValidation() {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateForm()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etFullName.addTextChangedListener(watcher)
        binding.etEmail.addTextChangedListener(watcher)
        binding.etPosition.addTextChangedListener(watcher)
    }

    private fun validateForm() {
        val name = binding.etFullName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val position = binding.etPosition.text.toString().trim()

        val isNameValid = name.isNotEmpty()
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPositionValid = position.isNotEmpty()

        binding.btnSave.isEnabled = isNameValid && isEmailValid && isPositionValid
    }

    private fun setupListeners() {
        binding.btnCancel.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnSave.setOnClickListener {
            // Task 2.2 will handle saving to Firestore
            // For now, we just close the activity to simulate success
            finish()
        }
    }
}