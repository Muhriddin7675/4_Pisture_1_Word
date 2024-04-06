package com.example.presentation.info

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.findwordkotlin.R

class InfoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        findViewById<View>(R.id.back_info).setOnClickListener {
            finish()
        }

        findViewById<View>(R.id.dispatcher).setOnClickListener {
            openTelegram()
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun openTelegram() {
        val telegramUri = Uri.parse("https://t.me/muhriddin7675")
        val intent = Intent(Intent.ACTION_VIEW, telegramUri)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            val webIntent = Intent(Intent.ACTION_VIEW, telegramUri)
            startActivity(webIntent)
        }
    }

}