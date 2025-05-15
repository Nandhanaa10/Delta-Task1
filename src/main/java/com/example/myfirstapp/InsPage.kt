package com.example.myfirstapp

import android.app.Activity
import android.content.Context
import android.os.Bundle

import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent;
import android.widget.ImageButton
import com.example.myfirstapp.R

class InsPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val backbutton = findViewById<ImageButton>(R.id.backbutton)
        backbutton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}