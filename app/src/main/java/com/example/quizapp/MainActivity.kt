package com.example.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private lateinit var nextbutton: Button
    private lateinit var previousbutton: Button
    private lateinit var questiontext: TextView

    //To determine current question
    private var currentIndex = 0

    //To determine the value for the generation of answers
    private var a = 0
    private var b = 0
    private var c = 0
    private var start = 0
    private var end = 0

    private val landmark = listOf(
        Questions(R.string.landmark1,"@string/France"),
        Questions(R.string.landmark2,"@string/USA"),
        Questions(R.string.landmark3,"@string/France"),
        Questions(R.string.landmark4,"@string/UK"),
        Questions(R.string.landmark5,"@string/UAE"),
        Questions(R.string.landmark6,"@string/USA"),
        Questions(R.string.landmark7,"@string/Germany"),
        Questions(R.string.landmark8,"@string/Spain"),
        Questions(R.string.landmark9,"@string/Malaysia"),
        Questions(R.string.landmark10,"@string/France")
    )

    private val answers = listOf(
        Answers(R.string.France),
        Answers(R.string.USA),
        Answers(R.string.UAE),
        Answers(R.string.Italy),
        Answers(R.string.UK),
        Answers(R.string.Germany),
        Answers(R.string.India),
        Answers(R.string.Egypt),
        Answers(R.string.Malaysia),
        Answers(R.string.Spain),
        Answers(R.string.Greece)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questiontext = findViewById(R.id.question_text)
        option1 = findViewById(R.id.button)
        option2 = findViewById(R.id.button2)
        option3 = findViewById(R.id.button3)
        option4 = findViewById(R.id.button4)

        updateQuestion()
        }


    //Random number generator
    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

    fun ranans() {
            a = rand(start, end)
            b = rand(start, end)
            c = rand(start, end)
    }

    private fun nextQuestion() {
        currentIndex = (currentIndex + 1) % landmark.size
        updateQuestion()
    }

    private fun previousQuestion() {
        currentIndex = (currentIndex + 1) % landmark.size
        updateQuestion()
    }

        private fun updateQuestion() {
            val questionTextResId = landmark[currentIndex].textResId
            questiontext.setText(questionTextResId)
            if (currentIndex == 0) {
                val buttonResId = answers[0].textResId
                option1.setText(buttonResId)
                var i = 0
                    start = 1
                    end = 10
                    ranans()
                    option2.setText(answers[a].textResId)
                    option3.setText(answers[b].textResId)
                    option4.setText(answers[c].textResId)
            }
            if (currentIndex == 1) {
                val buttonResId = answers[1].textResId
                option1.setText(buttonResId)
        }
            if (currentIndex == 2) {
                val buttonResId = answers[1].textResId
                option1.setText(buttonResId)
            }
            if (currentIndex == 3) {
                val buttonResId = answers[1].textResId
                option1.setText(buttonResId)
            }
            if (currentIndex == 4) {
                val buttonResId = answers[1].textResId
                option1.setText(buttonResId)
            }
            if (currentIndex == 5) {
                val buttonResId = answers[1].textResId
                option1.setText(buttonResId)
            }
            if (currentIndex == 6) {
                val buttonResId = answers[1].textResId
                option1.setText(buttonResId)
            }
            if (currentIndex == 7) {
                val buttonResId = answers[1].textResId
                option1.setText(buttonResId)
            }
            if (currentIndex == 8) {
                val buttonResId = answers[1].textResId
                option1.setText(buttonResId)
            }
            if (currentIndex == 9) {
                val buttonResId = answers[1].textResId
                option1.setText(buttonResId)
            }
            }

    private fun checkAnswer(userAnswer: String) {
        val correctAnswer = landmark[currentIndex].answer
}}