package com.test.ics26011_finals

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class LogScreen : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logscreen)

        //seekbar-------------------------------------
        val seekBar = findViewById<SeekBar>(R.id.logscreen_sb)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                updateResultText(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Not needed for this example
            }


        })
    }

    // Update the TextView based on the seek bar progress
    private fun updateResultText(progress: Int) {
        var resultTextView = findViewById<TextView>(R.id.update_result_tv)
        resultTextView.text = when (progress) {
            0 -> "Lose Weight"
            1 -> "Maintain"
            2 -> "Gain Weight"
            else -> "Unknown"
        }
    }

    //save the user details
    fun saveUserInfo(view: View) {
        //getting widgets
        val userGoals = findViewById<TextView>(R.id.update_result_tv)
        val userHeight = findViewById<EditText>(R.id.logscreen_et_height)
        val userWeight = findViewById<EditText>(R.id.logscreen_et_weight)

        //getting values of the widgets
        val goals = userGoals.text.toString()
        val height = userHeight.text.toString()
        val weight = userWeight.text.toString()


        //database handler
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (goals.trim() != "" &&
            height.trim() != "" &&
            weight.trim() != ""
        ) {
            val existingId: Int =
                -1// Retrieve the existing ID here if you have one, otherwise keep it as -1
            val status = databaseHandler.addUserData(
                EmpModelClass(
                    existingId,
                    goals,
                    height.toFloat(),
                    weight.toFloat()
                )
            )
            if (status > -1) {
                Toast.makeText(this, "Record Saved", Toast.LENGTH_LONG).show()
                userGoals.text
                userHeight.text.clear()
                userWeight.text.clear()

                val i = Intent(this, HomeScreen::class.java)
                startActivity(i)

            } else {
                Toast.makeText(this, "Error saving details.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Please fill out all the details.", Toast.LENGTH_LONG).show()
        }
    }


}
