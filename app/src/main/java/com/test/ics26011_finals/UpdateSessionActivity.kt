package com.test.ics26011_finals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.ics26011_finals.databinding.ActivityUpdateSessionBinding


class UpdateSessionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateSessionBinding
    private lateinit var db: SessionDatabaseHelper
    private var sessionId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = SessionDatabaseHelper(this)

        sessionId = intent.getIntExtra("session_id", -1)
        if (sessionId == -1) {
            finish()
            return
        }
        val session = db.getSessionByID(sessionId)
        binding.updateNameEditText.setText(session.name)
        binding.updateStartTimeEditText.setText(session.startTime)
        binding.updateEndTimeEditText.setText(session.endTime)
        binding.updateContentEditText.setText(session.content)

        binding.updateSaveButton.setOnClickListener {
            val newName = binding.updateNameEditText.text.toString()
            val newStartTime = binding.updateStartTimeEditText.text.toString()
            val newEndTime = binding.updateEndTimeEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()

            val updatedSession = Session(sessionId, newName, newStartTime, newEndTime, newContent)
            db.updateSession(updatedSession)
            finish()
            Toast.makeText(this, "Session Updated", Toast.LENGTH_LONG).show()
        }
    }
}