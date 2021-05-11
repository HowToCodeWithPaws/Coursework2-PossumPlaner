package com.example.attemptatautentification.ui.settings

import android.content.Intent
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.Subplan
import com.example.attemptatautentification.ui.deadlineEdit.DeadlineEditActivity
import java.util.ArrayList

class SettingsCategoryAdapter(private val categories: ArrayList<Category>) :
    RecyclerView.Adapter<SettingsCategoryAdapter.SettingsCategoryViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return SettingsCategoryViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: SettingsCategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        println("size = ${categories.size}")
        return categories.size
    }

    fun getItem(position: Int): Category {
        return categories[position]
    }

    class SettingsCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.category_name)

        @RequiresApi(Build.VERSION_CODES.O)
        var category: Category = Category()

        init {
            itemView.setOnClickListener {
                openCategoryScreenEdit(category)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(category_: Category) {
            category = category_
            name.text = category_.name

            println( category_.name+" "+ name.text.toString())
        }


        fun openCategoryScreenEdit(category_: Category) {
            categoryToEdit = category_
            userWithCategoryToEdit = userWithCategories
            val intent = Intent(parentSettingsCategories, CategoryEditActivity::class.java)
            parentSettingsCategories.startActivity(intent)
        }
    }
}