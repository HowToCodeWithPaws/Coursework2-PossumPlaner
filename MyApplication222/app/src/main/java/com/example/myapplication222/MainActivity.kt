package com.example.myapplication222

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
var set = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.text = "it aint much"
    }

    fun ChangeText(view: View) {
        if(!set){
            set = true
            button.text = "but it's honest work"

        }else{
            set = false
            button.text = "it aint much"
        }
    }
}