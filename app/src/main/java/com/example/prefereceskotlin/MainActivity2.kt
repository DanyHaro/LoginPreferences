package com.example.prefereceskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import com.example.prefereceskotlin.MainActivity.Companion.pref


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val textusuario:TextView = findViewById(R.id.textuser)
        val btnlogout:Button = findViewById(R.id.btnlogout)

        val username = pref.getName()
        textusuario.text=username

        btnlogout.setOnClickListener {
            pref.clearPreferences()
            onBackPressed()
        }
    }
}