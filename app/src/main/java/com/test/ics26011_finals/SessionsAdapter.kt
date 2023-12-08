package com.test.ics26011_finals

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SessionsAdapter(private var sessions: List<Session>, context: Context) :
    RecyclerView.Adapter<SessionsAdapter.SessionViewHolder>() {

    private val db: SessionDatabaseHelper = SessionDatabaseHelper(context)

    class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.session_item, parent, false)
        return SessionViewHolder(view)
    }

    override fun getItemCount(): Int = sessions.size

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val session = sessions[position]
        holder.nameTextView.text = session.name
        holder.contentTextView.text = session.content
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateSessionActivity::class.java).apply {
                putExtra("session_id", session.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener {
            db.deleteSession(session.id)
            refreshData(db.getAllSessions())
            Toast.makeText(holder.itemView.context, "Session Deleted", Toast.LENGTH_LONG).show()
        }
    }

    fun refreshData(newSessions: List<Session>) {
        sessions = newSessions
        notifyDataSetChanged()
    }
}