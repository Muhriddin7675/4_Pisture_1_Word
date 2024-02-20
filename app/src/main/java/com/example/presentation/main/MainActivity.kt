package com.example.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.service.autofill.CustomDescription
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AlertDialogLayout
import com.example.findwordkotlin.R
import com.example.presentation.Finish
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        val s = intent.getBooleanExtra("game",false)
        presenter = MainPresenter(this) as MainContract.Presenter
        presenter!!.setQuestion(s)
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        AlertDialog.Builder(this,R.style.CustomAlertDialog)
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                super.onBackPressed()
            }
            .setNegativeButton("No", null)
            .show()
    }
    private fun initialize() {
        money = findViewById(R.id.money)
        level = findViewById(R.id.level)
        backButton = findViewById(R.id.back)
        resetButton = findViewById(R.id.restart)
        backButton!!.setOnClickListener(View.OnClickListener { v: View? -> presenter?.menu() })
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
        AlertDialog.Builder(this,R.style.CustomAlertDialog)
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                super.onBackPressed()
            }
            .setNegativeButton("No", null)
            .show()
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
}

