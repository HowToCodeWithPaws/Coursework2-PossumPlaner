package com.example.attemptatautentification.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.attemptatautentification.BottomNavigationScreen
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.User
import com.google.gson.GsonBuilder

class UserSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)
    }

    fun deleteLocal(view: View) {
        val sharedPref = this.getSharedPreferences("User_saved", MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            this.clear()
            apply()
        }
        Toast.makeText(this, "данные удалены", Toast.LENGTH_SHORT).show()
    }

    fun logInWithNewAccount(view: View){
        //todo?
    }

    fun bindNewEmail(view: View){
        //todo?
    }
}