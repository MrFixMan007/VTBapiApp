package com.example.vtbapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityText: EditText = findViewById(R.id.cityEditText)
        val readyButton: Button = findViewById(R.id.readyButton)

        readyButton.setOnClickListener {
            val text = cityText.text.toString().trim()
            if (text == "") Toast.makeText(this, "Введите навзание города", Toast.LENGTH_SHORT).show()
            //TODO: добавить проверку на не хорошие символы, константы вместо хардкода, если много раз нажал, тоаст вывести один, шрифт, цвета, стартовая страница

        }
    }
}