package com.test.ics26011_finals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun toLogScreen(view: View) {
        val i = Intent(this, LogScreen::class.java)
        startActivity(i)
    }
}