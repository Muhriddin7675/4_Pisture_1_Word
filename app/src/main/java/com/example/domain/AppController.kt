package com.example.domain

import android.content.Context
import com.example.findwordkotlin.R
import com.example.data.model.QuestionData
import com.example.data.source.Pref

class AppController private constructor(context: Context) {
    companion object {
        lateinit var pref: Pref

        @Volatile
        private var instance: AppController? = null
        fun getInstance(context: Context): AppController {
            if (instance == null) {
                synchronized(this) {
                    instance = AppController(context)
                }
            }
            pref = Pref.getInstance(context)
            return instance!!
        }
    }

    fun saveLetters(count: Int) {

    }

    fun saveMoney(score: Int) = pref.saveMoney(score)
    fun getMoney(): Int = pref.getMoney()
    fun saveAnswers(answers: List<String>) = pref.saveAnswers(answers!!)
    fun getAnswers(): List<String> = pref.getAnswers()
    fun saveVariants(variants: List<Boolean?>) = pref.saveVariants(variants)
    fun getVariants(): List<Boolean> = pref.getVariants()
    fun saveLastLevel(level: Int) = pref.saveLastLevel(level)
    fun getLastLevel(): Int = pref.getLastLevel()
    fun getQuestionByLevel(level: Int): QuestionData? {

        return when (level) {
            0 -> QuestionData(
                R.drawable.win,
                R.drawable.win1,
                R.drawable.win2,
                R.drawable.win3,
                "CUP",
                "DSWYUIXCNBCP"
            )

            1 -> QuestionData(
                R.drawable.car,
                R.drawable.car1,
                R.drawable.car2,
                R.drawable.car3,
                "CAR",
                "KHRWAGVNCNOG"
            )

            2 -> QuestionData(
                R.drawable.ship,
                R.drawable.ship2,
                R.drawable.ship1,
                R.drawable.ship3,
                "SHIP",
                "SRCITSHDOMNP"
            )

            3 -> QuestionData(
                R.drawable.eatofusa,
                R.drawable.p3ofusa,
                R.drawable.p4ofusa,
                R.drawable.eat2ofusa,
                "AMERICA",
                "MBKAHIORIECA"
            )

            4 -> QuestionData(
                R.drawable.p1arabia,
                R.drawable.p4arabia,
                R.drawable.p3arabia,
                R.drawable.p2arabia,
                "SAUDIA",
                "DAIASDMUMKOP"
            )

            5 -> QuestionData(
                R.drawable.p1ofuzb,
                R.drawable.p4uzb,
                R.drawable.p3uzb,
                R.drawable.p2uzb,
                "UZB",
                "RXCBQZKSUMPK"
            )

            6 -> QuestionData(
                R.drawable.p1france,
                R.drawable.p4france,
                R.drawable.p3france,
                R.drawable.p2france,
                "FRANCE",
                "LOECMFBNAVRP"
            )

            7 -> QuestionData(
                R.drawable.p4koreas,
                R.drawable.p1korea,
                R.drawable.p2korea,
                R.drawable.p3koreasouth,
                "KOREA",
                "QVXREARKSHOP"
            )

            8 -> QuestionData(
                R.drawable.p4italy,
                R.drawable.p1italy,
                R.drawable.p2italy,
                R.drawable.p3italy,
                "ITALY",
                "VXTYBCNLADIC"
            )

            9 -> QuestionData(
                R.drawable.p1japan,
                R.drawable.p4japan,
                R.drawable.p3japan,
                R.drawable.p2japan,
                "JAPAN",
                "QWJBBNXAMANP"
            )

            10 -> QuestionData(
                R.drawable.p4mexico,
                R.drawable.p2mexico,
                R.drawable.p1mexico,
                R.drawable.p3mexico,
                "MEXICO",
                "XBMXEHCAJONI"
            )

            11 -> QuestionData(
                R.drawable.p1turkey,
                R.drawable.p3turkey,
                R.drawable.p4turkey,
                R.drawable.p2turkey,
                "TURKEY",
                "RNECBYTNUKNM"
            )

            12 -> QuestionData(
                R.drawable.p1poland,
                R.drawable.p4poland,
                R.drawable.p3poland,
                R.drawable.p2poland,
                "POLAND",
                "AKPSONDJKLNC"
            )


            else -> null
        }
    }
}