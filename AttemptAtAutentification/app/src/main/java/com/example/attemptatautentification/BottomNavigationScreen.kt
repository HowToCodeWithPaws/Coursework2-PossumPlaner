package com.example.attemptatautentification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.list.parentActivity
import com.example.attemptatautentification.ui.list.setNewUser
import com.example.attemptatautentification.ui.list.setParent
import com.example.attemptatautentification.ui.list.user
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.GsonBuilder

var passed_user = User()
class BottomNavigationScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_screen)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        //passing info to fragments
   //     val arguments = intent.extras
     //   passed_user = arguments!!["user_with_deadlines"] as User
        //     print(new_user.name + "\n" + new_user.plans[0].title + "\n" + new_user.plans[1].title)
        setNewUser(passed_user)
        setParent(this)
        com.example.attemptatautentification.ui.calendar.setNewUser(passed_user)
        com.example.attemptatautentification.ui.calendar.setParent(this)
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

    fun save() {
        val sharedPref = this.getSharedPreferences("User_saved" ,MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val data = passed_user
            val gson = GsonBuilder().create()
            putString("User", gson.toJson(data))
            println("выходим " + gson.toJson(data))
            println("user  " + passed_user.toString())
            apply()
        }
    }

    override fun onPause() {
        save()
        super.onPause()
    }
}