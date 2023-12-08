package com.test.ics26011_finals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.ics26011_finals.databinding.ActivitySessionsBinding

class SessionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySessionsBinding
    private lateinit var db: SessionDatabaseHelper
    private lateinit var sessionsAdapter: SessionsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySessionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = SessionDatabaseHelper(this)
        sessionsAdapter = SessionsAdapter(db.getAllSessions(), this)

        binding.sessionsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.sessionsRecyclerView.adapter = sessionsAdapter

        binding.addSessionButton.setOnClickListener {
            val intent = Intent(this, AddSessionActivity::class.java)
            startActivity(intent)


        }
    }

    override fun onResume() {
        super.onResume()
        sessionsAdapter.refreshData(db.getAllSessions())
    }
}