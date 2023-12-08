package com.test.ics26011_finals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)


    }

    fun toSessionsScreen(view: View) {
        val i = Intent(this, SessionsActivity::class.java)
        startActivity(i)
    }

    fun toUserScreen(view: View) {
        val i = Intent(this, UserScreen::class.java)
        startActivity(i)
    }

    fun toArticlesScreen(view: View) {
        val i = Intent(this, ArticlesScreen::class.java)
        startActivity(i)
    }

}
