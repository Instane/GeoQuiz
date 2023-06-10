package com.example.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var nextbutton: Button
    private lateinit var previousbutton: Button
    private lateinit var truebutton: Button
    private lateinit var falsebutton: Button
    private lateinit var questiontext: TextView
    private lateinit var resetbutton: Button


    //To determine current question
    private var currentIndex = 0

    //To store the index of the questions that has been answered
    private var IndexQuestion = intArrayOf()

    //Index for the amount of times cheated
    private var cheatIndex = 0

    private val landmark = listOf(
        Questions(R.string.landmark1,"yes"),
        Questions(R.string.landmark2,"yes"),
        Questions(R.string.landmark3,"yes"),
        Questions(R.string.landmark4,"no"),
        Questions(R.string.landmark5,"yes"),
        Questions(R.string.landmark6,"yes"),
        Questions(R.string.landmark7,"no"),
        Questions(R.string.landmark8,"yes"),
        Questions(R.string.landmark9,"yes"),
        Questions(R.string.landmark10,"yes")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        truebutton = findViewById(R.id.button6)
        falsebutton = findViewById(R.id.button5)
        questiontext = findViewById(R.id.question_text)
        previousbutton = findViewById(R.id.button4)
        nextbutton = findViewById(R.id.button3)
        resetbutton = findViewById(R.id.button7)

        updateQuestion()
        disablebuttons()

        //Declared here as XML text does not allow "<>" values
        nextbutton.setText("Next >")
        previousbutton.setText("< Prev")

        truebutton.setOnClickListener() {
            if (currentIndex == 9)
                currentIndex -= 1
            IndexQuestion = addElement(IndexQuestion, currentIndex)
            disablequestion()
            nextQuestion()
            disablebuttons()
        }

        falsebutton.setOnClickListener() {
            if (currentIndex == 9)
                currentIndex -= 1
            IndexQuestion = addElement(IndexQuestion, currentIndex)
            disablequestion()
            nextQuestion()
            disablebuttons()

        }

        nextbutton.setOnClickListener() {
            nextQuestion()
            disablebuttons()
        }

        previousbutton.setOnClickListener() {
            previousQuestion()
            disablebuttons()
        }

        updateQuestion()
        }

    //Disables buttons if questions are either first or last
    private fun disablebuttons() {
        if (currentIndex == 0)
            previousbutton.isEnabled = false
        if (currentIndex == 9)
            nextbutton.isEnabled = false
        if (currentIndex != 0)
            previousbutton.isEnabled = true
        if (currentIndex != 9)
            nextbutton.isEnabled = true
    }

    private fun nextQuestion() {
        currentIndex = (currentIndex + 1) % landmark.size
        updateQuestion()
    }

    private fun previousQuestion() {
        currentIndex = (currentIndex - 1) % landmark.size
        updateQuestion()
    }

        private fun updateQuestion() {
            val questionTextResId = landmark[currentIndex].textResId
            questiontext.setText(questionTextResId)
            }
    private fun reset() {

    }

    //Disables the question when user answers it
    fun disablequestion() {
        var i = 0
        do {
            var done = currentIndex
            var notdone = IndexQuestion[i]
            if (notdone == done)
                truebutton.isEnabled = false
                falsebutton.isEnabled = false
            if (notdone != done)
                truebutton.isEnabled = true
                falsebutton.isEnabled = true
            break
    }
        while (i < IndexQuestion.size)
    }

    //To determine whether the answer selected is correct or incorrect
    private fun checkAnswer(userAnswer: String) {
        val correctAnswer = landmark[currentIndex].answer
}
    //Answer option to display "Yes" and "No" for Boolean conversion
    fun String.toBoolean(): Boolean {
        when (this.toUpperCase()) {
            "yes" -> return true
            "no" -> return false
        }
        return false
    }
}

//To add element to array "IndexQuestion"
fun addElement(arr: IntArray, element: Int): IntArray {
    val mutableArray = arr.toMutableList()
    mutableArray.add(element)
    return mutableArray.toIntArray()
}
