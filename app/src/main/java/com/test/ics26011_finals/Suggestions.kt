package com.test.ics26011_finals

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Suggestions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestions)

        val buttonWorkoutPlan1: ImageButton = findViewById(R.id.button_plan1)
        buttonWorkoutPlan1.setOnClickListener {
            val url = "https://www.muscleandstrength.com/workouts/4-day-maximum-mass-workout"
            openUrl(url)
        }

        val buttonWorkoutPlan2: ImageButton = findViewById(R.id.button_plan2)
        buttonWorkoutPlan2.setOnClickListener {
            val url =
                "https://www.muscleandstrength.com/workouts/dumbbell-only-home-or-gym-fullbody-workout.html"
            openUrl(url)
        }

        val buttonWorkoutPlan3: ImageButton = findViewById(R.id.button_plan3)
        buttonWorkoutPlan3.setOnClickListener {
            val url =
                "https://www.muscleandstrength.com/workouts/3-day-dumbbell-only-workout-for-women"
            openUrl(url)
        }

        val buttonWorkoutPlan4: ImageButton = findViewById(R.id.button_plan4)
        buttonWorkoutPlan4.setOnClickListener {
            val url =
                "https://www.muscleandstrength.com/workouts/8-week-womens-beginner-fat-loss-workout"
            openUrl(url)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
