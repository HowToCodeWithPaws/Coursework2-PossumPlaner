package com.example.attemptatautentification

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.calendar.parentActivityCalendar
import com.example.attemptatautentification.ui.calendar.userCalendar
import com.example.attemptatautentification.ui.graph.parentActivityGraph
import com.example.attemptatautentification.ui.graph.userGraph
import com.example.attemptatautentification.ui.list.parentActivityList
import com.example.attemptatautentification.ui.list.userList
import com.example.attemptatautentification.ui.settings.categories.parentSettingsCategories
import com.example.attemptatautentification.ui.settings.parentActivitySettings
import com.example.attemptatautentification.ui.settings.userSettings
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder

var passed_user = User()

class BottomNavigationScreen : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_screen)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        for (item: Int in passed_user.plans.size - 1 downTo 0 step 1) {
            passed_user.plans[item].tryPutOff()
        }

        userList = passed_user
        parentActivityList = this
        userCalendar = passed_user
        parentActivityCalendar = this
        userSettings = passed_user
        parentActivitySettings = this
        userGraph = passed_user
        parentActivityGraph = this
        parentSettingsCategories = this

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
        val sharedPref = this.getSharedPreferences("User_saved", MODE_PRIVATE) ?: return
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