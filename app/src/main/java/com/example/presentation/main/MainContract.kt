package com.example.presentation.main

import com.example.data.model.QuestionData

interface MainContract {
    interface Model {
        fun getQuestionById(id: Int): QuestionData?
        fun getAnswers(): List<String>
        fun getVariants(): List<Boolean?>
        fun getLevel(): Int
        fun setLevel(level: Int)
        fun setAnswers(answers: List<String>)
        fun setVariants(variants: List<Boolean?>)
        fun saveMoney(money: Int)
        fun getMoney(): Int
    }

    interface View {
        fun startFinish()
        fun showDialogNext()
        fun showExitDialog()
        fun setImages(images: List<Int>)
        fun clearAnswer()
        fun setLevel(level: Int)
        fun setVariants(variants: String)
        fun setVisibleVariant(index: Int)
        fun setInvisibleVariant(index: Int)
        fun setAnswer(index: Int, answer: String?)
        fun setAnswersTextColor(color: Int)
        fun deleteAnswer(index: Int)
        fun showResult(s: String?)
        fun exit()
        fun setMoney(money: Int)
        fun setAnswerText(id: Int, letter: String)
    }

    interface Presenter {
        fun restart()
        fun nextLevel()
        fun menu()
        fun showExitDialog()
        fun setQuestion(newOrLoad: Boolean)
        fun clickAnswer(index: Int)
        fun clickVariant(index: Int)
        fun check()
        fun saveMoney()
        fun load()
        fun clickHelp()
    }
}
