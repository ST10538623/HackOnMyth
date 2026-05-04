package com.example.hackonmyth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackonmyth.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val finalScore = intent.getIntExtra("score", 0)
        val questionsList = intent.getSerializableExtra("questions") as? ArrayList<Question> ?: arrayListOf()
        val userAnswers = intent.getSerializableExtra("answers") as? ArrayList<Boolean> ?: arrayListOf()

        binding.scoreText.text = getString(R.string.label_score, finalScore, questionsList.size)

        binding.resultText.text = if (finalScore >= (questionsList.size / 2)) {
            getString(R.string.status_pro)
        } else {
            getString(R.string.status_learner)
        }

        binding.reviewBtn.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java).apply {
                putExtra("questions", questionsList)
                putExtra("answers", userAnswers)
            }
            startActivity(intent)
        }

        binding.resetBtn.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
            }
            finish()
        }
    }
}
