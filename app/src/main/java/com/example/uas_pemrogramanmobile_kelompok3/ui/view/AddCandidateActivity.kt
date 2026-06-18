package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.uas_pemrogramanmobile_kelompok3.R
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ActivityAddCandidateBinding
import com.example.uas_pemrogramanmobile_kelompok3.ui.viewmodel.CandidateViewModel

class AddCandidateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCandidateBinding
    private lateinit var viewModel: CandidateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCandidateBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CandidateViewModel::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupToolbar()
        setupValidation()
        setupListeners()
        observeViewModel()
    }

    private fun setupToolbar() {
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun setupValidation() {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateForm()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etCandidateName.addTextChangedListener(watcher)
        binding.etCandidateEmail.addTextChangedListener(watcher)
    }

    private fun validateForm() {
        val name = binding.etCandidateName.text.toString().trim()
        val email = binding.etCandidateEmail.text.toString().trim()

        val isNameValid = name.isNotEmpty()
        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

        binding.btnSaveCandidate.isEnabled = isNameValid && isEmailValid
    }

    private fun setupListeners() {
        binding.btnSaveCandidate.setOnClickListener {
            val name = binding.etCandidateName.text.toString().trim()
            val email = binding.etCandidateEmail.text.toString().trim()
            val position = "Umum" // Default position as it was removed from UI
            viewModel.addCandidate(name, email, position)
        }
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.btnSaveCandidate.isEnabled = !isLoading
            if (isLoading) {
                binding.btnSaveCandidate.text = getString(R.string.btn_saving)
            } else {
                binding.btnSaveCandidate.text = "Daftarkan & Generate Token"
            }
        }

        viewModel.addResult.observe(this) { result ->
            result.onSuccess { token ->
                val name = binding.etCandidateName.text.toString().trim()
                showTokenDialog(name, token)
            }.onFailure { exception ->
                val errorMsg = exception.message ?: getString(R.string.error_unknown)
                com.google.android.material.snackbar.Snackbar.make(binding.root, errorMsg, com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
                    .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, null))
                    .show()
            }
        }
    }

    private fun showTokenDialog(name: String, token: String) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_success_title))
            .setMessage(getString(R.string.dialog_success_message, token))
            .setPositiveButton(getString(R.string.btn_done)) { _, _ ->
                finish()
            }
            .setNeutralButton(getString(R.string.share_title)) { _, _ ->
                shareToken(name, token)
                finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun shareToken(name: String, token: String) {
        val shareText = getString(R.string.share_token_text, name, token)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)))
    }
}