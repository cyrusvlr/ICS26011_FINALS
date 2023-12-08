package com.test.ics26011_finals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.ics26011_finals.databinding.ActivityAddSessionBinding

class AddSessionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSessionBinding
    private lateinit var db: SessionDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = SessionDatabaseHelper(this)
        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val startTime = binding.startTimeEditText.text.toString()
            val endTime = binding.endTimeEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val session = Session(0, name, startTime, endTime, content)
            db.insertSession(session)
            finish()
            Toast.makeText(this, "Session Added", Toast.LENGTH_LONG).show()
        }
    }
}