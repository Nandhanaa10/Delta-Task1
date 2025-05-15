package com.example.myfirstapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.media3.common.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GamePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.gamelayout)
        val player1 = findViewById<CardView>(R.id.player1)
        val player2 = findViewById<CardView>(R.id.player2)
        val cannon = findViewById<ImageView>(R.id.cannondown)
        var isuserturn = true
        player1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.black))
        player2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cyan))
        player2.alpha=0.3f
        cannon.translationY=1400.0F
        cannon.rotation=180F
        val p1ship1=findViewById<ImageView>(R.id.player1ship1)
        val p1ship2=findViewById<ImageView>(R.id.player1ship2)
        val p1ship3=findViewById<ImageView>(R.id.player1ship3)
        val p1ship4=findViewById<ImageView>(R.id.player1ship4)
        val originalParents = mutableMapOf<Int, Int>()
        val comporiginalParents = mutableMapOf<Int, Int>()
        var countusermarks = 0
        var countcompmarks = 0
        var userpoints = 0
        var comppoints = 0
        var usertotalpoints = 0
        //for placing the ships in the cells selected by user from deploy page
        val json = intent.getStringExtra("ship_data")
        if (json != null) {
            val type = object : TypeToken<List<ShipPlacement>>() {}.type
            val placements: List<ShipPlacement> = Gson().fromJson(json, type)
            for (ship in placements) {
                val shipId = ship.shipId
                val cardId = ship.cardId
                val container = findViewById<ViewGroup>(cardId)
                val shipView = findViewById<View>(shipId)

                val parent = shipView.parent as? ViewGroup

                parent?.removeView(shipView)
                container.addView(shipView)
                shipView.alpha=0f
                shipView.tag="compship"
                container.setTag(R.id.ship_present,true)

            }
            val p1gridCells = listOf<CardView>(
                findViewById(R.id.a),
                findViewById(R.id.b),
                findViewById(R.id.c),
                findViewById(R.id.d),
                findViewById(R.id.e),
                findViewById(R.id.f),
                findViewById(R.id.g),
                findViewById(R.id.h),
                findViewById(R.id.j),
                findViewById(R.id.i),
                findViewById(R.id.k),
                findViewById(R.id.l),
                findViewById(R.id.m),
                findViewById(R.id.n),
                findViewById(R.id.o),
                findViewById(R.id.p),
                findViewById(R.id.q),
                findViewById(R.id.r),
                findViewById(R.id.s),
                findViewById(R.id.t),
                findViewById(R.id.u),
                findViewById(R.id.v),
                findViewById(R.id.w),
                findViewById(R.id.d1),
                findViewById(R.id.e1),
                findViewById(R.id.f1),
                findViewById(R.id.g1),
                findViewById(R.id.h1),
                findViewById(R.id.i1),
                findViewById(R.id.j1),
                findViewById(R.id.k1),
                findViewById(R.id.l1),
                findViewById(R.id.m1),
                findViewById(R.id.n1),
                findViewById(R.id.o1),
                findViewById(R.id.p1),



                )

            val p1ships = listOf<ImageView>(p1ship1,p1ship2,p1ship3,p1ship4)
            p1gridCells.forEach { it.visibility = View.VISIBLE }

            p1ships.forEach { ship ->
                val parent = ship.parent as? ViewGroup
                if (parent != null) {
                    originalParents[ship.id] = parent.id
                }
                parent?.removeView(ship)
            }

            val availableCells = p1gridCells.shuffled().toMutableList()
// computer randomly keeping the ships
            for (ship in p1ships) {


                if (availableCells.isNotEmpty()) {
                    val cell = availableCells.removeAt(0)
                    (cell as ViewGroup).addView(ship)
                    ship.alpha=0f
                    ship.tag="ship"
                    cell.setTag(R.id.ship_present, true)
                }
            }
// keeping cross and circle for the box selected by user

            p1gridCells.forEach { cell ->

                cell.setOnClickListener{

                    val image = ImageView(cell.context)
                    val hasShip = cell.getTag(R.id.ship_present) as? Boolean ?: false
                    if (hasShip) {
                        val shipView = findShipInCell(cell)
                        if (shipView != null) {
                            val originalCardId = originalParents[shipView.id]
                            val originalParent = findViewById<ViewGroup>(originalCardId!!)
                            (shipView.parent as? ViewGroup)?.removeView(shipView)
                            originalParent.addView(shipView)
                        }
                        image.setImageResource(R.drawable.redmarkwithblack) // add your tick drawable
                        cell.addView(image)
                        cell.isClickable = false
                        cell.isEnabled = false
                        userpoints += 10
                        Toast.makeText(this, GlobalData.slogan, Toast.LENGTH_SHORT).show()
                        countusermarks++
                        if (countusermarks==4){
                            for (i in p1gridCells) {
                                i.isClickable = false
                                i.isEnabled = false
                            }
                            val intent = Intent(this,EndPage::class.java)
                            intent.putExtra("Winner","PLAYER WINS")
                            usertotalpoints = userpoints*10
                            intent.putExtra("Score",usertotalpoints)
                            startActivity(intent)
                        }

                    } else {
                        image.setImageResource(R.drawable.crosswithblack) // add your cross drawable
                        cell.addView(image)
                        cell.isClickable = false
                        cell.isEnabled = false
                        userpoints -= 1
                    }
                    isuserturn = false

                    val compgridCells = listOf<CardView>(
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
                    val compship1=findViewById<ImageView>(R.id.player2ship1)
                    val compship2=findViewById<ImageView>(R.id.player2ship2)
                    val compship3=findViewById<ImageView>(R.id.player2ship3)
                    val compship4=findViewById<ImageView>(R.id.player2ship4)
                    val compships = listOf<ImageView>(compship1,compship2,compship3,compship4)

                    player2.alpha=1f
                    player1.alpha=0.3f
                    cannon.translationY=200.0F
                    cannon.rotation=0F

                    val available = compgridCells.filter { it.getTag(R.id.clicked) != true }
                    Handler(Looper.getMainLooper()).postDelayed({


                        if (available.isNotEmpty()) {
                            val chosencell = available.random()
                            chosencell.setTag(R.id.clicked,true)

                            val compimage = ImageView(chosencell.context)
                            val comphasShip = chosencell.getTag(R.id.ship_present) as? Boolean ?: false
                            if (comphasShip) {
                                val compshipView = findShipInCell(chosencell)
                                if (compshipView != null) {
                                    val comporiginalCardId = comporiginalParents[compshipView.id]
                                    val comporiginalParent = findViewById<ViewGroup>(comporiginalCardId!!)
                                    (compshipView.parent as? ViewGroup)?.removeView(compshipView)
                                    comporiginalParent.addView(compshipView)
                                }
                                compimage.setImageResource(R.drawable.redmark) // add your tick drawable
                                chosencell.addView(compimage)
                                chosencell.isClickable = false
                                chosencell.isEnabled = false

                                countcompmarks++
                                comppoints+=10
                                if (countcompmarks==4){
                                    for (j in compgridCells) {
                                        j.isClickable = false
                                        j.isEnabled = false
                                    }
                                    val intent = Intent(this,EndPage::class.java)
                                    intent.putExtra("Winner","COMPUTER WINS")
                                    usertotalpoints = userpoints*10
                                    intent.putExtra("Score",usertotalpoints)
                                    startActivity(intent)
                                }

                            } else {
                                compimage.setImageResource(R.drawable.cross) // add your cross drawable
                                chosencell.addView(compimage)
                                chosencell.isClickable = false
                                chosencell.isEnabled = false
                                comppoints-=1
                            }}



                        isuserturn = true
                        player2.alpha=0.3f
                        player1.alpha=1f
                        cannon.translationY=1400.0F
                        cannon.rotation=180F


                    }, 2000)




                }

                }
            }




    }

    }




fun findShipInCell(cell: View): View? {
    if (cell is ViewGroup) {
        for (i in 0 until cell.childCount) {
            val child = cell.getChildAt(i)
            val isShip = child.getTag(R.id.ship_view) as? Boolean ?: false
            if (isShip) return child
        }
    }
    return null
}


