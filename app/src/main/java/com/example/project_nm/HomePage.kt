package com.example.project_nm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {

    lateinit var bottomnav: BottomNavigationView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomnav = findViewById(R.id.BottomNav)

        if (savedInstanceState ==null){
            supportFragmentManager.beginTransaction().replace(R.id.FrameLayout, HomePageMain()).commit()
        }
        bottomnav.setOnItemSelectedListener{item->

            when(item.itemId){
                R.id.home ->{
                    supportFragmentManager.beginTransaction().replace(R.id.FrameLayout,
                        HomePageMain()).commit()
                    true
                }
                R.id.Tips ->{
                    supportFragmentManager.beginTransaction().replace(R.id.FrameLayout, Tips()).commit()
                    true
                }
                R.id.nearby ->{
                    supportFragmentManager.beginTransaction().replace(R.id.FrameLayout,
                        NearByLocations()).commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(R.id.FrameLayout,
                        Profile()).commit()
                    true
                }


                        else  -> false
            }
        }
    }

}


