package com.example.presentation.splesh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.findwordkotlin.R
import com.example.presentation.start.StartActivity

class SpleshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splesh)

        Handler().postDelayed({

            startActivity(Intent(this@SpleshActivity, StartActivity::class.java))
            finish() // Optional: finish the current activity if needed

        }, 1000)
    }
}