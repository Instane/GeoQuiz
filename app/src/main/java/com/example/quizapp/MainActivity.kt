package com.example.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var nextbutton: Button
    private lateinit var previousbutton: Button
    private lateinit var truebutton: Button
    private lateinit var falsebutton: Button
    private lateinit var questiontext: TextView
    private lateinit var resetbutton: Button
    private lateinit var cheattext: TextView
    private lateinit var cheatbutton: Button
    private lateinit var cheatpanel: CardView
    private lateinit var closebutton: Button

    //To determine current question
    private var currentIndex = 0

    //To store the index of the questions that has been answered
    private var IndexQuestion = intArrayOf()

    //Index for the allowed number of cheats = 3
    private var cheatIndex = 3

    //The total score from the test
    private var correctans = 0
    private var wrongans = 0
    private var score = 0

    //Declaration in list for question "landmarks"
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

    //Main Instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        truebutton = findViewById(R.id.button6)
        falsebutton = findViewById(R.id.button5)
        questiontext = findViewById(R.id.question_text3)
        previousbutton = findViewById(R.id.button4)
        nextbutton = findViewById(R.id.button3)
        resetbutton = findViewById(R.id.button7)
        cheattext = findViewById(R.id.question_text)
        cheatbutton = findViewById(R.id.button8)
        cheatpanel = findViewById(R.id.card_cheats)
        closebutton = findViewById(R.id.button10)

        updateQuestion()
        disablebuttons()

        //Declared here as XML text does not allow "<>" values
        nextbutton.setText("Next >")
        previousbutton.setText("< Prev")
        cheattext.setText("Number of cheat attempts left: " + cheatIndex)
        cheatpanel.isVisible = false

        //Detects if the "YES" button is activated
        truebutton.setOnClickListener() {
            if (currentIndex == 9)
                currentIndex -= 1
            IndexQuestion = addElement(IndexQuestion, currentIndex)
            nextQuestion()
            disablebuttons()
            disablequestion()
            checkAnswer("yes")
        }

        //Detects if the "NO" button is activated
        falsebutton.setOnClickListener() {
            if (currentIndex == 9)
                currentIndex -= 1
            IndexQuestion = addElement(IndexQuestion, currentIndex)
            nextQuestion()
            disablebuttons()
            disablequestion()
            checkAnswer("no")

        }

        //Detects if the "NEXT" button is activated
        nextbutton.setOnClickListener() {
            nextQuestion()
            disablebuttons()
            disablequestion()
        }

        //Detects if the "PREV" button is activated
        previousbutton.setOnClickListener() {
            previousQuestion()
            disablebuttons()
            disablequestion()
        }

        //Detects if the "RESET" button is activated
        resetbutton.setOnClickListener() {
            reset()
            disablebuttons()

        }

        //Detect if the "CHEAT" button is activated
        cheatbutton.setOnClickListener() {
            cheat()
            disablebuttons()
        }

        closebutton.setOnClickListener() {
            if(cheatIndex > 0)
                cheatpanel.isVisible = false
                cheatbutton.isVisible = true
            if (cheatIndex <= 0)
                cheatbutton.isEnabled = false
                cheatpanel.isVisible = false
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

    private fun cheat() {
        if (cheatIndex > 0)
            cheatIndex -= 1
            cheattext.setText("Number of cheat attempts left: " + cheatIndex)
            cheatpanel.isVisible = true
            cheatbutton.isVisible = false
        if (cheatIndex <= 0)
            cheattext.setText("No more cheat attempts")

    }

    //Changed to the next question
    private fun nextQuestion() {
        currentIndex = (currentIndex + 1) % landmark.size
        updateQuestion()
    }

    //Changed to the previous question
    private fun previousQuestion() {
        currentIndex = (currentIndex - 1) % landmark.size
        updateQuestion()
    }

    //Changes questions if buttons "yes", "no", "previous", "next" has been activated
        private fun updateQuestion() {
            val questionTextResId = landmark[currentIndex].textResId
            questiontext.setText(questionTextResId)
            }

    //Resets all the questions in within the quiz
    private fun reset() {
        for (i in IndexQuestion) {
            if (IndexQuestion.isEmpty())
                updateQuestion()
            else
                IndexQuestion = removeElement(IndexQuestion, i)
                currentIndex = 0
                wrongans = 0
                correctans = 0
                updateQuestion()

    }}

    //Disables the question when user answers it
    fun disablequestion() {
        if (IndexQuestion.contains(currentIndex)) {
            truebutton.isEnabled = false
            falsebutton.isEnabled = false
        }
        if (currentIndex !in IndexQuestion) {
            truebutton.isEnabled = true
            falsebutton.isEnabled = true
        } }

    //To determine whether the answer selected is correct or incorrect
    private fun checkAnswer(userAnswer: String) {
        val correctAnswer = landmark[currentIndex].answer

        //If currentIndex is not 9, then it is before the last question
        if (currentIndex != 9) {
            val messageResId = if (userAnswer == correctAnswer) {
                correctans += 1
            }
            else {
                wrongans += 1
            }
            }

        //If currentIndex is 9, it will compile all user score
        if (currentIndex == 9) {
            val messageResId = if (userAnswer == correctAnswer) {
                correctans += 1
            }
            else {
                wrongans += 1
            }
}}
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

//To remove element from array "IndexQuestion" when resetting quiz
fun removeElement(arr: IntArray, element: Int): IntArray {
    val mutableArray = arr.toMutableList()
    mutableArray.remove(element)
    return mutableArray.toIntArray()
}
