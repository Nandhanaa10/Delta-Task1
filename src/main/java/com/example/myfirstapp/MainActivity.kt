package com.example.myfirstapp

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
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
            val insButton = findViewById<ImageView>(R.id.QuestionmarkImage)
            val settButton = findViewById<ImageView>(R.id.settingsImage)
            val title = findViewById<TextView>(R.id.BattleshipArmada)
            val playButton = findViewById<ImageView>(R.id.PlayImage)
            val shared = getSharedPreferences("Data3", MODE_PRIVATE)
            val hasshown = shared.getBoolean("hasshown",false)
            if (!hasshown){
                dialog()
                shared.edit() { putBoolean("hasshown", true) }
            }

        // moving to instructions page
        insButton.setOnClickListener{
                val intent = Intent(this,InsPage::class.java)
                startActivity(intent)
            }
        //moving to settings page
        settButton.setOnClickListener{
            val intent = Intent(this,settpage::class.java)
            startActivity(intent)
        }
        //moving to game page
        playButton.setOnClickListener {
            val intent = Intent(this,DeployPage::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()
    }
    override fun onRestart() {
        super.onRestart()
    }
    override fun onResume() {
        super.onResume()

    }
    override fun onStop() {
        super.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()

    }

// pop up dialog
    private fun dialog(){
        val builder = AlertDialog.Builder(this,R.style.Dialogtheme)
        builder.setTitle("Game Instructions")
        builder.setMessage("Welcome to Strategic Fleet Battle – a tactical twist on the classic game of Battleship! Here's everything you need to know to play:\n" +
                "\n" +
                "\uD83C\uDFAF Objective\n" +
                "Be the first to completely destroy your opponent’s fleet by choosing smart attacks or strategic defenses.\n" +
                "\n" +
                "\uD83D\uDD39 Starting the Game\n" +
                "Each player begins with a hidden fleet of ships placed on a grid.\n" +
                "\n" +
                "Ships can be of varying lengths and are positioned before the match begins.\n" +
                "\n" +
                "\uD83D\uDD01 Taking Turns\n" +
                "Players alternate turns. On each turn, a player must choose one of the following actions:\n" +
                "\n" +
                "⚔\uFE0F Attack\n" +
                "Select a tile on your opponent’s grid to launch an attack.\n" +
                "\n" +
                "If a ship occupies the tile, it takes damage.\n" +
                "\n" +
                "If no ship is present, the attack misses.\n" +
                "\n" +
                "Damaged ships remain in place but can no longer be moved.\n" +
                "\n" +
                "\uD83D\uDEE1\uFE0F Fortify (Defend)\n" +
                "Instead of attacking, reposition any undamaged ship on your grid.\n" +
                "\n" +
                "Use this to avoid predicted attacks or strengthen your defense.\n" +
                "\n" +
                "Only undamaged ships can be moved. Once a ship is hit, it’s locked in place.\n" +
                "\n" +
                "\uD83C\uDFA8 Turn Indicators\n" +
                "The background color of the game interface will change to show whose turn it is.\n" +
                "\n" +
                "\uD83C\uDFC1 Victory Condition\n" +
                "The game continues until one player's entire fleet is destroyed.\n" +
                "\n" +
                "The player with remaining ships is declared the winner.\n")

        builder.setPositiveButton("OK",null)

        val dialog = builder.create()
        dialog.show()
    }

}

object GlobalData {
    var playerScore: Int = 0
    var highestScore: Int=0
    var slogan: String="Way to go"
}

