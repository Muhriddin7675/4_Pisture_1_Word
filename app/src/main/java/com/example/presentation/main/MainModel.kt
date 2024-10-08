package com.example.presentation.main

import android.content.Context
import com.example.data.model.QuestionData
import com.example.domain.AppController
import com.example.presentation.main.MainContract


class MainModel(context: Context) : MainContract.Model {
    private val appController: AppController
    init {
        appController = AppController.getInstance(context)
    }

    override fun getQuestionById(id: Int): QuestionData? {
        return appController.getQuestionByLevel(id)
    }

    override fun getAnswers(): List<String> {
        return appController.getAnswers()
    }

    override fun getVariants(): List<Boolean> {
        return appController.getVariants()
    }

    override fun getLevel(): Int {
        return appController.getLastLevel()
    }

    override fun setLevel(level: Int) {
        appController.saveLastLevel(level)
    }

    override fun setAnswers(answers: List<String>) {
        appController.saveAnswers(answers)
    }

    override fun setVariants(variants: List<Boolean?>) {
        appController.saveVariants(variants)
    }

    override fun saveMoney(money: Int) {
        appController.saveMoney(money)
    }

    override fun getMoney(): Int {
        return appController.getMoney()
    }
}

