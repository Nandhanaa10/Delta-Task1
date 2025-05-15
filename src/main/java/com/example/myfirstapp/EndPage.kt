package com.example.myfirstapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class EndPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.endlayout)
        val card = findViewById<CardView>(R.id.containsall)
        card.bringToFront()
        val home = findViewById<Button>(R.id.homebutton)
        val playagain = findViewById<Button>(R.id.playagain)
        home.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        playagain.setOnClickListener {
            val intent = Intent(this,DeployPage::class.java)
            startActivity(intent)
        }
        val msg = intent.getStringExtra("Winner")
        val existingmsg = findViewById<TextView>(R.id.winner)
        existingmsg.text=msg
        val score = intent.getIntExtra("Score",0)
        val newscore = findViewById<TextView>(R.id.svalue)
        newscore.text=score.toString()
        GlobalData.playerScore=score

        if(GlobalData.playerScore>GlobalData.highestScore){
            GlobalData.highestScore=GlobalData.playerScore
        }
        val highscore = GlobalData.highestScore
        val oldhighscore = findViewById<TextView>(R.id.hsvalue)
        oldhighscore.text=highscore.toString()
    }
}

