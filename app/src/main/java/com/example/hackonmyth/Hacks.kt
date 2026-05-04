package com.example.hackonmyth

import java.io.Serializable

data class Question(
    val text: String,
    val answer: Boolean,
    val explanation: String
) : Serializable

object Hacks {

    fun getQuestions(): ArrayList<Question> {
        return arrayListOf(
            Question(
                "Putting your phone in rice fixes water damage",
                false,
                "Rice does not effectively remove moisture inside electronics."
            ),
            Question(
                "Drinking water improves concentration",
                true,
                "Proper hydration helps brain performance and focus."
            ),
            Question(
                "Cracking knuckles causes arthritis",
                false,
                "There is no scientific evidence linking it to arthritis."
            ),
            Question(
                "Cold showers increase alertness",
                true,
                "They stimulate blood flow and wake up your body."
            ),
            Question(
                "Charging your phone overnight damages it",
                false,
                "Modern smartphones stop charging once full."
            )
        )
    }
}