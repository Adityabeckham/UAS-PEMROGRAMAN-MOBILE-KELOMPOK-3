package com.example.uas_pemrogramanmobile_kelompok3.ui.view

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uas_pemrogramanmobile_kelompok3.R
import com.example.uas_pemrogramanmobile_kelompok3.data.model.Question
import com.example.uas_pemrogramanmobile_kelompok3.databinding.ActivityExamBinding
import java.util.Locale
import java.util.concurrent.TimeUnit

class ExamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExamBinding
    private var currentQuestionIndex = 0
    private val questions = mutableListOf<Question>()
    private val answers = mutableMapOf<Int, Int>() // Question Index -> Selected Option Index
    
    // Task 3.2 (Max): Timer & Anti-Cheat
    private var countDownTimer: CountDownTimer? = null
    private val examDurationMillis: Long = 15 * 60 * 1000L // 15 Menit
    private var isExamFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadDummyQuestions()
        setupUI()
        setupListeners()
        displayQuestion()
        startTimer()
    }

    private fun loadDummyQuestions() {
        questions.addAll(listOf(
            Question("1", "Apa kepanjangan dari APK pada Android?", listOf("Android Package Kit", "Android Programming Kit", "Application Package Kit", "Application Programming Kit"), 0),
            Question("2", "Bahasa pemrograman utama untuk pengembangan Android modern adalah?", listOf("Java", "Kotlin", "Swift", "C++"), 1),
            Question("3", "Komponen Android yang digunakan untuk antarmuka pengguna adalah?", listOf("Service", "Broadcast Receiver", "Activity", "Content Provider"), 2),
            Question("4", "File manifest pada Android bernama?", listOf("AndroidManifest.xml", "Config.xml", "AppManifest.json", "System.xml"), 0),
            Question("5", "Layout yang menyejajarkan komponen secara horizontal atau vertikal adalah?", listOf("ConstraintLayout", "LinearLayout", "FrameLayout", "RelativeLayout"), 1)
        ))
    }

    private fun setupUI() {
        binding.examProgress.max = questions.size
    }

    private fun setupListeners() {
        binding.btnNext.setOnClickListener {
            saveCurrentAnswer()
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion()
            }
        }

        binding.btnPrevious.setOnClickListener {
            saveCurrentAnswer()
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                displayQuestion()
            }
        }

        binding.btnFinish.setOnClickListener {
            showFinishConfirmation()
        }
    }

    private fun displayQuestion() {
        val question = questions[currentQuestionIndex]
        
        binding.examProgress.progress = currentQuestionIndex + 1
        binding.tvQuestionIndicator.text = getString(R.string.question_indicator, currentQuestionIndex + 1, questions.size)
        
        binding.tvQuestionText.text = question.text
        binding.rbOptionA.text = question.options[0]
        binding.rbOptionB.text = question.options[1]
        binding.rbOptionC.text = question.options[2]
        binding.rbOptionD.text = question.options[3]

        binding.rgOptions.clearCheck()
        answers[currentQuestionIndex]?.let { selectedIndex ->
            when (selectedIndex) {
                0 -> binding.rbOptionA.isChecked = true
                1 -> binding.rbOptionB.isChecked = true
                2 -> binding.rbOptionC.isChecked = true
                3 -> binding.rbOptionD.isChecked = true
            }
        }

        binding.btnPrevious.visibility = if (currentQuestionIndex == 0) View.GONE else View.VISIBLE
        
        if (currentQuestionIndex == questions.size - 1) {
            binding.btnNext.visibility = View.GONE
            binding.btnFinish.visibility = View.VISIBLE
        } else {
            binding.btnNext.visibility = View.VISIBLE
            binding.btnFinish.visibility = View.GONE
        }
    }

    private fun saveCurrentAnswer() {
        val selectedId = binding.rgOptions.checkedRadioButtonId
        if (selectedId != -1) {
            val selectedIndex = when (selectedId) {
                R.id.rbOptionA -> 0
                R.id.rbOptionB -> 1
                R.id.rbOptionC -> 2
                R.id.rbOptionD -> 3
                else -> -1
            }
            answers[currentQuestionIndex] = selectedIndex
        }
    }

    // Task 3.2 (Max): Timer Implementation
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(examDurationMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                binding.tvTimer.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                
                // Alert if time is running out (less than 1 minute)
                if (millisUntilFinished < 60000) {
                    binding.tvTimer.setTextColor(getColor(R.color.error))
                }
            }

            override fun onFinish() {
                handleTimeUp()
            }
        }.start()
    }

    private fun handleTimeUp() {
        saveCurrentAnswer()
        isExamFinished = true
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.error_time_up_title))
            .setMessage(getString(R.string.error_time_up_message))
            .setPositiveButton(getString(R.string.btn_done)) { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

    // Task 3.2 (Max): Basic Anti-Cheat
    override fun onPause() {
        super.onPause()
        if (!isExamFinished) {
            Toast.makeText(this, getString(R.string.warning_anti_cheat_message), Toast.LENGTH_LONG).show()
        }
    }

    private fun showFinishConfirmation() {
        saveCurrentAnswer()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.confirm_finish_title))
            .setMessage(getString(R.string.confirm_finish_message))
            .setPositiveButton(getString(R.string.btn_yes)) { _, _ ->
                isExamFinished = true
                countDownTimer?.cancel()
                finish()
            }
            .setNegativeButton(getString(R.string.btn_no), null)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}