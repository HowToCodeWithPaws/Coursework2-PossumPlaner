package com.example.attemptatautentification.ui.settings.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attemptatautentification.BottomNavigationScreen
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.User
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_settings_categories_screen.*

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