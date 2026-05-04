package com.example.hackonmyth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hackonmyth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(
            this,
            getString(R.string.welcome_message),
            Toast.LENGTH_SHORT
        ).show()

        binding.startBtn.setOnClickListener { view ->
            // Subtle feedback animation
            view.animate()
                .scaleX(0.92f)
                .scaleY(0.92f)
                .setDuration(80)
                .withEndAction {
                    view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(80)
                        .start()

                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                }
                .start()
        }
    }
}
