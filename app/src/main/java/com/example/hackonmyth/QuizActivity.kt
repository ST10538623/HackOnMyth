package com.example.hackonmyth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackonmyth.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var challenges: ArrayList<Question>

    private var currentPos = 0
    private var correctCount = 0
    private val selectedAnswers = ArrayList<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Randomize questions for a unique experience
        challenges = Hacks.getQuestions().apply { shuffle() }

        updateUi()

        binding.trueBtn.setOnClickListener { validateResponse(true) }
        binding.falseBtn.setOnClickListener { validateResponse(false) }
        binding.nextBtn.setOnClickListener { moveToNext() }
    }

    private fun updateUi() {
        val current = challenges[currentPos]
        binding.questionText.text = current.text
        binding.feedbackText.text = ""
        binding.nextBtn.isEnabled = false
        binding.trueBtn.isEnabled = true
        binding.falseBtn.isEnabled = true
    }

    private fun validateResponse(userPick: Boolean) {
        val actual = challenges[currentPos].answer
        selectedAnswers.add(userPick)

        if (userPick == actual) {
            binding.feedbackText.text = getString(R.string.feedback_correct)
            correctCount++
        } else {
            binding.feedbackText.text = getString(R.string.feedback_wrong)
        }

        // Lock buttons after selection
        binding.trueBtn.isEnabled = false
        binding.falseBtn.isEnabled = false
        binding.nextBtn.isEnabled = true
    }

    private fun moveToNext() {
        currentPos++

        if (currentPos < challenges.size) {
            updateUi()
        } else {
            val resultsIntent = Intent(this, ScoreActivity::class.java).apply {
                putExtra("score", correctCount)
                putExtra("questions", challenges)
                putExtra("answers", selectedAnswers)
            }
            startActivity(resultsIntent)
            finish()
        }
    }
}
