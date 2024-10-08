package com.example.presentation.main

import android.content.Context
import android.graphics.Color
import android.util.Log
import com.example.data.model.QuestionData
import com.example.presentation.dialog.HelpDialog
import com.example.presentation.dialog.HelpListener
import java.util.Locale

class MainPresenter(view: MainContract.View) : MainContract.Presenter {
    private var money = 0
    private val MAX_LENGTH: Int = 8
    private var level = 0
    private val answers: ArrayList<String?> = ArrayList()
    private val variants: ArrayList<Boolean> = ArrayList()
    private var view: MainContract.View? = view
    private var model: MainContract.Model? = null
    private var answerHelp = 0
    private var answerHelpList = ArrayList<Int>()

    init {
        this.model = MainModel(view as Context)
    }

    override fun clickAnswer(index: Int) {
        val clickedAnswer = answers[index]
        if (clickedAnswer == null || clickedAnswer == "") {
            return
        }
        val questionData = model!!.getQuestionById(level)
        val variants: String = questionData!!.variants
        view!!.setAnswersTextColor(Color.WHITE)
        for (i in 0 until variants.length) {
            val currentVariant = variants[i].toString()
            if (clickedAnswer == currentVariant && this.variants[i]) {
                answers[index] = null
                view!!.setAnswer(index, "")
                view!!.setVisibleVariant(i)
                this.variants[i] = false
                return
            }
        }
    }

    override fun clickVariant(index: Int) {
        var freeIndex = answers.indexOf(null)
        if (freeIndex == -1) return
        val questionData = model!!.getQuestionById(level)
        answers[freeIndex] = java.lang.String.valueOf(questionData!!.variants[index])
        view!!.setAnswer(
            freeIndex,
            java.lang.String.valueOf(questionData.variants[index])
        )
        view!!.setInvisibleVariant(index)
        variants[index] = true
        freeIndex = answers.indexOf(null)
        if (freeIndex == -1) check()

    }

    override fun check() {
        val userAnswers = StringBuilder()
        for (answer in answers) {
            if (answer != null) {
                userAnswers.append(answer)
            }
        }

        val questionData = model!!.getQuestionById(level)
        val correctAnswer: String = questionData!!.answer

        if (userAnswers.toString() != correctAnswer) {
            view!!.showResult(" Your $userAnswers answer is wrong -5 coin :( !")
            if (money > 0) {
                money -= 5
                view!!.setAnswersTextColor(Color.RED)
            }
            view!!.setMoney(money)
            return
        }
        view!!.setAnswersTextColor(Color.GREEN)
        view!!.showDialogNext()
        money += 25
        saveMoney()
        view!!.setMoney(money)
        model!!.setLevel(level + 1)
    }

    override fun saveMoney() {
        model!!.saveMoney(money)
    }

    override fun load() {
    }

    override fun restart() {
        for (i in answers.indices) {
            clickAnswer(i)
        }
    }

    override fun nextLevel() {
        level++
        answers.clear()
        variants.clear()
        setQuestion(false)
        answerHelp = 0
        answerHelpList.clear()
        view!!.setAnswersTextColor(Color.parseColor("#FAED7A"))
    }

    override fun menu() {
        Log.d("TTT", "exit: menu ")
        view!!.exit()
    }

    override fun setQuestion(newOrLoad: Boolean) {
        level = model!!.getLevel()
        if (newOrLoad) {
            money = 100;
            model!!.saveMoney(money)
            level = 0
            model!!.setLevel(level)
        }
        if (level > 12) {
            model!!.setLevel(0)
            view!!.startFinish()
            return
        }
        money = model!!.getMoney()
        view!!.setMoney(money)
        val question: QuestionData? = model!!.getQuestionById(level)
        view!!.clearAnswer()
        for (i in 0 until MAX_LENGTH) {
            view!!.setAnswerText(i,"")
            if (question!!.answer.length <= i) {
                view!!.deleteAnswer(i)
            } else {
                answers.add(null)
            }
        }
        view!!.setLevel(level + 1)
        view!!.setImages(question!!.getImages())
        view!!.setVariants(question.variants)
        for (i in 0 until question.variants.length) {
            variants.add(false)
        }
    }

    override fun clickHelp() {
        val questionData = model!!.getQuestionById(level)
        val variants: String = questionData!!.answer
        val freeIndex = answers.indexOf(null)
        var result = true
        if(answerHelpList.isNotEmpty()){
            answerHelpList.forEach { it ->
                if (freeIndex == it) {
                    result = false
                }
            }
        }
        if(result && freeIndex != -1) {
            if(money >= 20){
                if (answerHelp < variants.length) {
                    view!!.showHelpDialog()
                }
            }
            else{
                view!!.showResult("Your coins are not enough!")
            }
        } else{
            view!!.showResult("You can get help after filling in the correct option!")

        }
    }

    override fun showExitDialog() {
        view!!.showExitDialog()
    }

    override fun clickHelpDialogYesButton() {
        val questionData = model!!.getQuestionById(level)
        val variants: String = questionData!!.answer
        val freeIndex = answers.indexOf(null)
        money -= 20
        saveMoney()
        view!!.setMoney(money)
        view!!.setAnswerText(
            freeIndex,
            variants[freeIndex].toString().toUpperCase(Locale.ROOT)
        )
        answerHelpList.add(freeIndex)
        answerHelp++

    }
    fun myLog(msg: String) {
        Log.d("TTT", msg)
    }
}