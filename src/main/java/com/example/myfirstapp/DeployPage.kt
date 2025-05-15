package com.example.myfirstapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.DragEvent
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.google.gson.Gson



class DeployPage : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.deploylayout)
        val save= findViewById<Button>(R.id.save)
        val player2=findViewById<CardView>(R.id.player2)
        player2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cyan))
        val ship1 = findViewById<ImageView>(R.id.player2ship1)
        ship1.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val shadow = View.DragShadowBuilder(view)
                view.startDragAndDrop(null, shadow, view, 0)
                true
            } else {
                false
            }
        }
        val ship2 = findViewById<ImageView>(R.id.player2ship2)
        ship2.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val shadow = View.DragShadowBuilder(view)
                view.startDragAndDrop(null, shadow, view, 0)
                true
            } else {
                false
            }
        }
        val ship3 = findViewById<ImageView>(R.id.player2ship3)
        ship3.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val shadow = View.DragShadowBuilder(view)
                view.startDragAndDrop(null, shadow, view, 0)
                true
            } else {
                false
            }
        }
        val ship4 = findViewById<ImageView>(R.id.player2ship4)
        val tick = findViewById<ImageButton>(R.id.tick)
        val del = findViewById<ImageButton>(R.id.delete)
        ship4.setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val shadow = View.DragShadowBuilder(view)
                view.startDragAndDrop(null, shadow, view, 0)
                true
            } else {
                false
            }
        }
        val c1=findViewById<CardView>(R.id.a2)
        val gridCells = listOf<CardView>(
            findViewById(R.id.a2),
            findViewById(R.id.b2),
            findViewById(R.id.c2),
            findViewById(R.id.d2),
            findViewById(R.id.e2),
            findViewById(R.id.f2),
            findViewById(R.id.g2),
            findViewById(R.id.h2),
            findViewById(R.id.j2),
            findViewById(R.id.i2),
            findViewById(R.id.k2),
            findViewById(R.id.l2),
            findViewById(R.id.m2),
            findViewById(R.id.n2),
            findViewById(R.id.o2),
            findViewById(R.id.p2),
            findViewById(R.id.q2),
            findViewById(R.id.r2),
            findViewById(R.id.s2),
            findViewById(R.id.t2),
            findViewById(R.id.u2),
            findViewById(R.id.v2),
            findViewById(R.id.w2),
            findViewById(R.id.x2),
            findViewById(R.id.y2),
            findViewById(R.id.z2),
            findViewById(R.id.a12),
            findViewById(R.id.b12),
            findViewById(R.id.c12),
            findViewById(R.id.d12),
            findViewById(R.id.e12),
            findViewById(R.id.f12),
            findViewById(R.id.g12),
            findViewById(R.id.h12),
            findViewById(R.id.i12),
            findViewById(R.id.j12),

            )
        val dragTouchListener = View.OnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val shadowBuilder = View.DragShadowBuilder(view)
                view.startDragAndDrop(null, shadowBuilder, view, 0)
                view.alpha = 0.5f
                return@OnTouchListener true
            }
            false
        }
        gridCells.forEach { card ->
            card.setOnDragListener { view, event ->
                val cell = view as CardView
                when (event.action) {
                    DragEvent.ACTION_DRAG_ENTERED -> {
                        cell.alpha = 0.3f
                        true
                    }
                    DragEvent.ACTION_DRAG_EXITED -> {
                        cell.alpha = 1f
                        true
                    }
                    DragEvent.ACTION_DROP -> {
                        val ship = event.localState as? View ?: return@setOnDragListener false
                        val parent = ship.parent as ViewGroup //
                        parent.removeView(ship)
                        val container = view as ViewGroup
                        container.addView(ship)
                        ship.x = 0f
                        ship.y = 0f
                        ship.alpha = 1f
                        tick.setOnClickListener{
                            ship.setOnTouchListener(null)
                        }
                        del.setOnClickListener{
                            ship.setOnTouchListener(dragTouchListener)
                        }
                        true
                    }

                    DragEvent.ACTION_DRAG_ENDED -> {
                        cell.alpha = 1f
                        true
                    }

                    else -> true
                }
            }
        }
        val ships = listOf<ImageView>(ship1,ship2,ship3,ship4)  // Your ship views
        val auto = findViewById<Button>(R.id.auto)
        auto.setOnClickListener {
            // Reset grid
            gridCells.forEach { it.visibility = View.VISIBLE }

            // Detach ships from current parents
            ships.forEach { ship ->
                val parent = ship.parent as? ViewGroup
                parent?.removeView(ship)
            }

            val availableCells = gridCells.shuffled().toMutableList()

            for (ship in ships) {


                if (availableCells.isNotEmpty()) {
                    val cell = availableCells.removeAt(0)
                    (cell as ViewGroup).addView(ship)

                }
            }
        }

        save.setOnClickListener {
            val placements = mutableListOf<ShipPlacement>()

            ships.forEach { ship ->
                val parent = ship.parent as? View
                if (parent != null) {
                    placements.add(
                        ShipPlacement(
                            shipId = ship.id,
                            cardId = parent.id,
                        )
                    )
                }
            }
            val gson = Gson()
            val json = gson.toJson(placements)
            val intent = Intent(this,GamePage::class.java)
            intent.putExtra("ship_data", json)
            startActivity(intent)
        }

    }
}
data class ShipPlacement(
    val shipId: Int,
    val cardId: Int,
)
