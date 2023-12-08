package com.test.ics26011_finals

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ArticlesScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articlesscreen)

        val buttonForHealthyDiet: ImageButton = findViewById(R.id.button_for_healthy_diet)
        buttonForHealthyDiet.setOnClickListener {
            val url = "https://www.hsph.harvard.edu/nutritionsource/healthy-eating-plate/"
            openUrl(url)
        }

        val buttonForPosture: ImageButton = findViewById(R.id.button_for_posture)
        buttonForPosture.setOnClickListener {
            val url =
                "https://www.canr.msu.edu/news/regular_breathing_and_proper_posture_when_exercising_is_important" // Replace with the actual URL
            openUrl(url)
        }

        val buttonForSimpleExercise: ImageButton = findViewById(R.id.button_for_simple_exercises)
        buttonForSimpleExercise.setOnClickListener {
            val url =
                "https://www.self.com/gallery/5-basic-exercise-moves-everyone-needs-to-know" // Replace with the actual URL
            openUrl(url)
        }

        val buttonforWeightlifting: ImageButton = findViewById(R.id.button_for_weightlifting)
        buttonforWeightlifting.setOnClickListener {
            val url =
                "https://www.healthline.com/health/how-to-start-lifting-weights" // Replace with the actual URL
            openUrl(url)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}