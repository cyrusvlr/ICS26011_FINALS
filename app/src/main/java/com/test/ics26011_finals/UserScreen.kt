package com.test.ics26011_finals

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class UserScreen : AppCompatActivity() {
    private lateinit var userGoals: TextView  // Declare as a class-level property

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userscreen)


        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val empModelClass: List<EmpModelClass> = databaseHandler.viewUserData()

        if (empModelClass.isNotEmpty()) {
            val userDetails = empModelClass[0]

            val userGoalsTextView = findViewById<TextView>(R.id.update_result_tv)
            val userHeightView = findViewById<TextView>(R.id.userscreen_tv_height)
            val userWeightView = findViewById<TextView>(R.id.userscreen_tv_weight)

            userGoalsTextView.text = "${userDetails.userGoals}"
            userHeightView.text = "${userDetails.userHeight} cm"
            userWeightView.text = "${userDetails.userWeight} kg"
        } else {
            Toast.makeText(this, "No user details available", Toast.LENGTH_SHORT).show()
        }
        userGoals = findViewById(R.id.update_result_tv)  // Initialize the class-level property

    }


    @SuppressLint("MissingInflatedId")
    fun updateUserInfo(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.updateuserdetails, null)
        dialogBuilder.setView(dialogView)

        val seekBar = dialogView.findViewById<SeekBar>(R.id.update_sb)
        val userGoals = dialogView.findViewById<TextView>(R.id.updateuserdetails_result_tv)


        //seekbar
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateResultText(userGoals, progress) // Pass userGoals TextView
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })


        //get widgets
        val userHeight = dialogView.findViewById<EditText>(R.id.update_et_height)
        val userWeight = dialogView.findViewById<EditText>(R.id.update_et_weight)

        //updating database
        dialogBuilder.setTitle("Update your details")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->
            val userUpdateGoals = userGoals.text.toString()
            val userUpdateHeight = userHeight.text.toString()
            val userUpdateWeight = userWeight.text.toString()

            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (userUpdateGoals.trim() != ""
                && userUpdateHeight.trim() != ""
                && userUpdateWeight.trim() != ""
            ) {

                val status = databaseHandler.updateUserData(
                    EmpModelClass(
                        userInfoID = 1,
                        userUpdateGoals,
                        userUpdateHeight.toFloat(),
                        userUpdateWeight.toFloat()
                    )
                )

                if (status > -1) {
                    Toast.makeText(this, "Your details have been updated", Toast.LENGTH_LONG).show()

                    //update automatically details on user screen
                    val userDetails = databaseHandler.viewUserData()
                    if (userDetails.isNotEmpty()) {
                        val updatedDetails = userDetails[0]

                        val userGoalsTextView = findViewById<TextView>(R.id.update_result_tv)
                        val userHeightTextView = findViewById<TextView>(R.id.userscreen_tv_height)
                        val userWeightTextView = findViewById<TextView>(R.id.userscreen_tv_weight)

                        userGoalsTextView.text = "${updatedDetails.userGoals}"
                        userHeightTextView.text = "${updatedDetails.userHeight}"
                        userWeightTextView.text = "${updatedDetails.userWeight}"
                    }
                }
            }
        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
        })
        val b = dialogBuilder.create()
        b.show()
    }

    private fun updateResultText(resultTextView: TextView, progress: Int) {
        resultTextView.text = when (progress) {
            0 -> "Lose Weight"
            1 -> "Maintain"
            2 -> "Gain Weight"
            else -> "Unknown"
        }
    }


}
