package com.example.attemptatautentification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.list.setNewUser
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_screen)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        //passing info to fragments
        val arguments = intent.extras
        val new_user = arguments!!["user_with_deadlines"] as User
   //     print(new_user.name + "\n" + new_user.plans[0].title + "\n" + new_user.plans[1].title)
        setNewUser(new_user)

        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.navigation_list,
                        R.id.navigation_calendar,
                        R.id.navigation_graph,
                        R.id.navigation_settings
                )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}