package com.example.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.findwordkotlin.R
import com.example.presentation.Finish
import com.example.presentation.dialog.ExitDialog
import com.example.presentation.dialog.ExitListener
import com.example.presentation.dialog.HelpDialog
import com.example.presentation.dialog.HelpListener
import com.example.presentation.dialog.MyDialog
import com.example.presentation.dialog.SelectListener


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), MainContract.View {
    private var money: TextView? = null
    private var images: MutableList<ImageView>? = null
    private var answers: MutableList<Button> = ArrayList()
    private var variants: MutableList<Button> = ArrayList()
    private var level: TextView? = null
    private var presenter: MainContract.Presenter? = null
    private var backButton: ImageView? = null
    private var resetButton: ImageView? = null
    private lateinit var helpButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        clickHelpButton()
        val s = intent.getBooleanExtra("game", false)
        presenter = MainPresenter(this) as MainContract.Presenter
        presenter!!.setQuestion(s)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                presenter!!.showExitDialog()
            }
        }
        this.onBackPressedDispatcher.addCallback(callback)
    }


    private fun initialize() {
        money = findViewById(R.id.money)
        level = findViewById(R.id.level)
        backButton = findViewById(R.id.back)
        resetButton = findViewById(R.id.restart)
        helpButton = findViewById(R.id.help)
        backButton!!.setOnClickListener(View.OnClickListener { v: View? -> presenter!!.showExitDialog() })
        resetButton!!.setOnClickListener(View.OnClickListener { v: View? -> presenter!!.restart() })
        images = ArrayList()
        images!!.add(findViewById(R.id.imgQuestion1))
        images!!.add(findViewById(R.id.imgQuestion2))
        images!!.add(findViewById(R.id.imgQuestion3))
        images!!.add(findViewById(R.id.imgQuestion4))
        answers.addAll(findButtons(
            R.id.linerAnswers, 0
        ) { view: View -> clickAnswer(view) })
        variants.addAll(findButtons(
            R.id.linerVariant1, 0
        ) { view: View -> clickVariant(view) })
        variants.addAll(findButtons(
            R.id.linerVariant2, variants.size
        ) { view: View -> clickVariant(view) })


    }

    private fun clickHelpButton() {
        helpButton.setOnClickListener {
            presenter!!.clickHelp()
        }
    }

    private fun clickAnswer(view: View) {
        presenter!!.clickAnswer((view.tag as Int))
    }

    private fun clickVariant(view: View) {
        presenter!!.clickVariant((view.tag as Int))
    }

    private fun findButtons(
        linerId: Int,
        position: Int,
        listener: View.OnClickListener
    ): List<Button> {
        val buttons: MutableList<Button> = ArrayList()
        val layout = findViewById<LinearLayout>(linerId)
        for (i in 0 until layout.childCount) {
            val button = layout.getChildAt(i) as Button
            button.tag = i + position
            button.setOnClickListener(listener)
            buttons.add(button)
        }
        return buttons
    }

    override fun showDialogNext() {
        val dialog = MyDialog()
        dialog.setSelectListener(object : SelectListener {
            override operator fun next() {
                presenter!!.nextLevel()
            }

            override fun menu() {
                presenter!!.menu()
            }
        })
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, "test")
    }

    override fun showExitDialog() {
        val dialog = ExitDialog("Are you sure you want to exit")
        dialog.setExitListener(object : ExitListener {
            override fun yes() {
                finish()
            }

            override fun no() {
            }
        })
        dialog.show(supportFragmentManager, "exit dialog")
    }

    override fun setImages(images: List<Int>) {
        for (i in images.indices) {
            this.images!![i].setImageResource(images[i])
        }
    }

    override fun clearAnswer() {
        for (i in answers.indices) {
            answers[i].visibility = View.VISIBLE
            answers[i].text = ""
        }
    }

    override fun setLevel(level: Int) {
        this.level!!.text = level.toString()
    }

    @SuppressLint("SetTextI18n")
    override fun setMoney(money: Int) {
        this.money!!.text = money.toString()
    }

    override fun setVariants(variants: String) {
        for (i in this.variants.indices) {
            this.variants[i].visibility = View.VISIBLE
            this.variants[i].text = variants[i].toString()
        }
    }

    override fun setVisibleVariant(index: Int) {
        variants[index].visibility = View.VISIBLE
    }

    override fun setInvisibleVariant(index: Int) {
        variants[index].visibility = View.INVISIBLE
    }

    override fun setAnswer(index: Int, answer: String?) {
        answers[index].text = answer
    }

    override fun deleteAnswer(index: Int) {
        if (index >= 0 && index < answers.size) {
            answers[index].visibility = View.GONE
        }
    }

    override fun showResult(s: String?) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun exit() {
        finish()
        presenter!!.nextLevel()
    }

    override fun onStop() {
        presenter!!.saveMoney()
        super.onStop()
    }

    override fun startFinish() {
        val intent = Intent(this@MainActivity, Finish::class.java)
        startActivity(intent)
        finish()
    }

    override fun setAnswersTextColor(color: Int) {
        answers.forEach {
            it.setTextColor(color)
        }
    }

    override fun setAnswerText(id: Int, letter: String) {
        answers[id].hint = letter
        answers[id].setHintTextColor(Color.YELLOW)
    }

    override fun showHelpDialog() {
        val helpDialog = HelpDialog()
        helpDialog.setExitListener(object : HelpListener {
            override fun yes() {
                presenter!!.clickHelpDialogYesButton()
            }

            override fun no() {
            }
        })
        helpDialog.show(supportFragmentManager,"help dialog")

    }


}

