package com.example.domain

import android.content.Context
import com.example.findwordkotlin.R
import com.example.data.model.QuestionData
import com.example.findwordkotlin.data.source.Pref

class AppController private constructor(context: Context) {
    companion object {
        lateinit var pref: Pref

        @Volatile
        private var instance: AppController? = null;
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
                "tiger",
                "ditvbtcarfeg",
                R.drawable.lv_tiger_1,
                R.drawable.lv_tiger_2,
                R.drawable.lv_tiger_3,
                R.drawable.lv_tiger_4
            )

            1 -> QuestionData(
                "elephant",
                "tlepahjanecb",
                R.drawable.lv_elephant_1,
                R.drawable.lv_elephant_2,
                R.drawable.lv_elephant_3,
                R.drawable.lv_elephant_4
            )

            2 -> QuestionData(
                "lion",
                "nljfnkbaleio",
                R.drawable.lv_lion_1,
                R.drawable.lv_lion_2,
                R.drawable.lv_lion_3,
                R.drawable.lv_lion_4
            )

            3 -> QuestionData(
                "giraffe",
                "fgifmarcegkb",
                R.drawable.lv_giraffe_1,
                R.drawable.lv_giraffe_2,
                R.drawable.lv_giraffe_3,
                R.drawable.lv_giraffe_4
            )

            4 -> QuestionData(
                "zebra",
                "ruaebjdrbafz",
                R.drawable.lv_zebra_1,
                R.drawable.lv_zebra_2,
                R.drawable.lv_zebra_3,
                R.drawable.lv_zebra_4
            )

            5 -> QuestionData(
                "kangaroo",
                "ajarkionhrgo",
                R.drawable.lv_kangaroo_1,
                R.drawable.lv_kangaroo_2,
                R.drawable.lv_kangaroo_3,
                R.drawable.lv_kangaroo_4
            )

            6 -> QuestionData(
                "cheetah",
                "hecatiuedcsh",
                R.drawable.lv_cheetah_1,
                R.drawable.lv_cheetah_2,
                R.drawable.lv_cheetah_3,
                R.drawable.lv_cheetah_4
            )

            7 -> QuestionData(
                "panda",
                "nepusfcdaiba",
                R.drawable.lv_panda_1,
                R.drawable.lv_panda_2,
                R.drawable.lv_panda_3,
                R.drawable.lv_panda_4
            )

            8 -> QuestionData(
                "dolphin",
                "hopdidreboln",
                R.drawable.lv_dolphin_1,
                R.drawable.lv_dolphin_2,
                R.drawable.lv_dolphin_3,
                R.drawable.lv_dolphin_4
            )

            9 -> QuestionData(
                "koala",
                "haldidkebola",
                R.drawable.lv_koala_1,
                R.drawable.lv_koala_2,
                R.drawable.lv_koala_3,
                R.drawable.lv_koala_4
            )

            10 -> QuestionData(
                "penguin",
                "gnpdidreuoln",
                R.drawable.lv_penguin_1,
                R.drawable.lv_penguin_2,
                R.drawable.lv_penguin_3,
                R.drawable.lv_penguin_4
            )

            11 -> QuestionData(
                "gorilla",
                "loadigreroln",
                R.drawable.lv_gorilla_1,
                R.drawable.lv_gorilla_2,
                R.drawable.lv_gorilla_3,
                R.drawable.lv_gorilla_4
            )

            12 -> QuestionData(
                "monkey",
                "hopyikrebmln",
                R.drawable.lv_monkey_1,
                R.drawable.lv_monkey_2,
                R.drawable.lv_monkey_3,
                R.drawable.lv_monkey_4
            )

            13 -> QuestionData(
                "rhino",
                "hordidreboln",
                R.drawable.lv_rhino_1,
                R.drawable.lv_rhino_2,
                R.drawable.lv_rhino_3,
                R.drawable.lv_rhino_4
            )

            14 -> QuestionData(
                "turtle",
                "hrptidretolu",
                R.drawable.lv_turtle_1,
                R.drawable.lv_turtle_2,
                R.drawable.lv_turtle_3,
                R.drawable.lv_turtle_4
            )

            15 -> QuestionData(
                "bear",
                "hapdidreboln",
                R.drawable.lv_bear_1,
                R.drawable.lv_bear_2,
                R.drawable.lv_bear_3,
                R.drawable.lv_bear_4
            )

            16 -> QuestionData(
                "wolf",
                "hwpdfdreboln",
                R.drawable.lv_wolf_1,
                R.drawable.lv_wolf_2,
                R.drawable.lv_wolf_3,
                R.drawable.lv_wolf_4
            )

            17 -> QuestionData(
                "cat",
                "toadidrecoln",
                R.drawable.lv_cat_1,
                R.drawable.lv_cat_2,
                R.drawable.lv_cat_3,
                R.drawable.lv_cat_4
            )

            18 -> QuestionData(
                "fox",
                "hopxidrebofn",
                R.drawable.lv_fox_1,
                R.drawable.lv_fox_2,
                R.drawable.lv_fox_3,
                R.drawable.lv_fox_4
            )

            19 -> QuestionData(
                "owl",
                "hodidrweboln",
                R.drawable.lv_owl_1,
                R.drawable.lv_owl_2,
                R.drawable.lv_owl_3,
                R.drawable.lv_owl_4
            )
            20 -> QuestionData(
                "eagle",
                "eogdidrebaln",
                R.drawable.lv_eagle_4,
                R.drawable.lv_eagle_2,
                R.drawable.lv_eagle_3,
                R.drawable.lv_eagle_4
            )
            21 -> QuestionData(
                "octopus",
                "coptsucebolp",
                R.drawable.lv_octopus_1,
                R.drawable.lv_octopus_2,
                R.drawable.lv_octopus_3,
                R.drawable.lv_octopus_4
            )
            22 -> QuestionData(
                "snake",
                "hspdikaeboln",
                R.drawable.lv_snake_1,
                R.drawable.lv_snake_2,
                R.drawable.lv_snake_3,
                R.drawable.lv_snake_4
            )

            23 -> QuestionData(
                "butterfly",
                "tfpdtdrebylu",
                R.drawable.lv_butterfly_1,
                R.drawable.lv_butterfly_2,
                R.drawable.lv_butterfly_3,
                R.drawable.lv_butterfly_4
            )


            else -> null
        }
    }
}