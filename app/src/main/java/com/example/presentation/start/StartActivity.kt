package com.example.presentation.start

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.findwordkotlin.R
import com.example.presentation.main.MainActivity


class StartActivity : AppCompatActivity(), StartContract.View {
    private var playButton: AppCompatButton? = null
    private var exitButton: AppCompatButton? = null
    private var newGame: AppCompatButton? = null
    private var presenter: StartContract.Presenter? = null
    private lateinit var money: TextView
    private lateinit var level: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        presenter = StartPresenter(this)
        initialize()
    }

    private fun initialize() {
        money = findViewById(R.id.money)
        money.text = "${presenter!!.getMoney()}"
        level = findViewById(R.id.level)
        level.text = presenter!!.getLevel().toString()
        playButton = findViewById<View>(R.id.loadButton) as AppCompatButton
        exitButton = findViewById<View>(R.id.exitButton) as AppCompatButton
        newGame = findViewById<View>(R.id.newButton) as AppCompatButton
        newGame!!.setOnClickListener { view: View? -> presenter!!.newGame() }
        playButton!!.setOnClickListener { view: View? -> presenter!!.start() }
        exitButton!!.setOnClickListener { view: View? -> presenter!!.exit() }
    }

    override fun start(i: Int) {
        val intent = Intent(this@StartActivity, MainActivity::class.java)
        if (i == 1) {
            intent.putExtra("game", true)
        }
        startActivity(intent)

    }

    override fun exit() {
        AlertDialog.Builder(this,R.style.CustomAlertDialog).apply {
            setMessage("Do you want to exit the game? :(")
                .setTitle("Exit?")
                .setPositiveButton(
                "Yes"
            ) { dialog: DialogInterface?, which: Int -> finish() }
            .setNegativeButton(
                "No"
            ) { dialog: DialogInterface?, which: Int -> }
            .create().show()
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        money.text = "${presenter!!.getMoney()}"
        level.text = (presenter!!.getLevel() + 1).toString()
        super.onResume()
    }
}