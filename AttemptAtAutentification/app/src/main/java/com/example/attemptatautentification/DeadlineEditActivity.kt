package com.example.attemptatautentification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.attemptatautentification.auth.AuthManager
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.list.setNewUser
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.common.io.Files.append
import kotlinx.android.synthetic.main.activity_deadline_edit_screen.*
import kotlinx.android.synthetic.main.activity_main.*

class DeadlineEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deadline_edit_screen)

        val arguments = intent.extras
        val deadline = arguments!!["deadline"] as Plan
        deadline_title.text = deadline.title
        deadline_category.text = StringBuilder(deadline_category.text).append(deadline.category.name)
        deadline_importance.text = StringBuilder(deadline_importance.text).append(deadline.importance.toString())
        deadline_deadline.text = StringBuilder(deadline_deadline.text).append(deadline.deadline.toString()).toString()
        deadline_redline.text = StringBuilder(deadline_redline.text).append(deadline.redline.toString()).toString()

    }
}