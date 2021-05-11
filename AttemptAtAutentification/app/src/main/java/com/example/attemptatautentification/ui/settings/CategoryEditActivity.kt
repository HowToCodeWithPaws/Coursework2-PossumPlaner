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
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.Subplan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.deadlineEdit.DeadlineEditAdapter
import com.example.attemptatautentification.ui.deadlineEdit.deadlineToEdit
import com.example.attemptatautentification.ui.deadlineEdit.userToEdit
import com.example.attemptatautentification.ui.list.user
import kotlinx.android.synthetic.main.activity_category_edit.*
import kotlinx.android.synthetic.main.activity_deadline_edit_screen.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
var categoryToEdit: Category = Category()
var userWithCategoryToEdit: User = User()

class CategoryEditActivity : AppCompatActivity() {

    var adapter: CategoryEditAdapter? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_edit)

        category_title.setText( categoryToEdit.name)

        refresh(rv_plans)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun refresh(rvList: RecyclerView) {
        adapter = CategoryEditAdapter( userWithCategories.plans.filter { it.category.name.equals(
            categoryToEdit.name) } as ArrayList<Plan> )
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this.applicationContext)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun categorySave(view: View) {
        userWithCategoryToEdit.deleteCategory(categoryToEdit)
        userWithCategoryToEdit.addCategory(Category( category_title.text.toString()))
       // categoryToEdit.name =

        this.finish()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun categoryDelete(view: View) {
        userWithCategoryToEdit.deleteCategory(categoryToEdit)

        this.finish()
    }
}