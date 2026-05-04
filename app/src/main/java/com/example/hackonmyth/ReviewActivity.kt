package com.example.hackonmyth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackonmyth.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val questions = intent.getSerializableExtra("questions") as? ArrayList<Question> ?: arrayListOf()
        val answers = intent.getSerializableExtra("answers") as? ArrayList<Boolean> ?: arrayListOf()

        val summary = StringBuilder()

        questions.forEachIndexed { index, question ->
            val userAnswer = answers.getOrNull(index) ?: false
            val isCorrect = userAnswer == question.answer

            summary.append("${index + 1}. ${question.text}\n")
            summary.append("Your Choice: ${if (userAnswer) "Hack" else "Myth"} ")
            summary.append(if (isCorrect) "✅" else "❌")
            summary.append("\nExplanation: ${question.explanation}\n\n")
        }

        binding.reviewText.text = summary.toString()

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}
