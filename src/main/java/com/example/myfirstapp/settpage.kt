package com.example.myfirstapp

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class settpage : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settingslayout)
        val gobackbutton = findViewById<ImageButton>(R.id.goback)
        gobackbutton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val highvalue = findViewById<EditText>(R.id.higgscorevalue)
        highvalue.isEnabled=false
        highvalue.setText(GlobalData.highestScore.toString())
        val accvalue = findViewById<EditText>(R.id.accuracyvalue)
        val rank = findViewById<EditText>(R.id.rankingvalue)
        accvalue.isEnabled=false
        rank.isEnabled=false
        if(GlobalData.highestScore!=0){
            accvalue.setText(((GlobalData.playerScore/GlobalData.highestScore)*100).toString())}

        val checkbox1 = findViewById<CheckBox>(R.id.checkBox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkBox4)
        val checkbox2 = findViewById<CheckBox>(R.id.checkBox3)


    }
}
