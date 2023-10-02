package com.example.vtbapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slidingUpLayout : SlidingUpPanelLayout = findViewById(R.id.slidingUpLayout)
        slidingUpLayout.panelHeight = 300
        slidingUpLayout.anchorPoint = 0.5f
        slidingUpLayout.coveredFadeColor = resources.getColor(R.color.transparent)
    }
}