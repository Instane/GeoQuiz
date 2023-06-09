package com.example.quizapp

import androidx.annotation.StringRes

data class Questions(@StringRes val textResId: Int, val answer: String) {
}

data class Answers(@StringRes val textResId: Int) {}