package com.example.attemptatautentification.ui.settings

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.BottomNavigationScreen
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.Subplan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.deadlineEdit.DeadlineEditAdapter
import com.example.attemptatautentification.ui.deadlineEdit.deadlineToEdit
import com.example.attemptatautentification.ui.deadlineEdit.userToEdit
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_deadline_edit_screen.*
import kotlinx.android.synthetic.main.activity_settings_categories_screen.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

var userWithCategories: User = User()
var parentSettingsCategories: BottomNavigationScreen = BottomNavigationScreen()

class SettingsCategoriesScreen : AppCompatActivity(), AdapterView.OnItemSelectedListener {


    var adapter: SettingsCategoryAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings_categories_screen)

       adapter = SettingsCategoryAdapter(userWithCategories.categories)
        rv_categories.adapter = adapter
        rv_categories.layoutManager = LinearLayoutManager(this.applicationContext)

        add_category.setOnClickListener {
            var new_category: Category = Category()
            adapter?.notifyDataSetChanged()
            userWithCategories.addCategory(new_category)
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }

    fun save() {
        val sharedPref = this.getSharedPreferences("User_saved", MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val data = userWithCategories
            val gson = GsonBuilder().create()
            putString("User", gson.toJson(data))
            println("выходим " + gson.toJson(data))
            println("user to edit  " + userWithCategories.toString())
            apply()
        }
    }

    override fun onPause() {
        save()
        super.onPause()
    }
}