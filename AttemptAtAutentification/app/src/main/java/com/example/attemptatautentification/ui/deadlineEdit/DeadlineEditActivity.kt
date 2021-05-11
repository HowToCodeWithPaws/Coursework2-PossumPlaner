package com.example.attemptatautentification.ui.deadlineEdit

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.R
import com.example.attemptatautentification.passed_user
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.Subplan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.list.user
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_deadline_edit_screen.*
import kotlinx.android.synthetic.main.deadline_item.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.O)
var deadlineToEdit: Plan = Plan()
var userToEdit: User = User()

class DeadlineEditActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var adapter: DeadlineEditAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_deadline_edit_screen)
        deadline_importance.rating = deadlineToEdit.importance.toFloat()
        deadline_title.setText(deadlineToEdit.title)
        deadline_notes.setText(deadlineToEdit.notes)
        deadline_finished.isChecked = deadlineToEdit.isFinished

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        var formattedDate = deadlineToEdit.date.format(formatter)
        var formattedDeadline = deadlineToEdit.deadline.format(formatter)
        deadline_deadline.setText(formattedDeadline)
        deadline_date.setText(formattedDate)
        var calDed = Calendar.getInstance()
        var calDate = Calendar.getInstance()

        val dateSetListenerDeadline =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calDed.set(Calendar.YEAR, year)
                calDed.set(Calendar.MONTH, monthOfYear)
                calDed.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                deadline_deadline.setText(sdf.format(calDed.time))
            }

        val dateSetListenerDate =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calDate.set(Calendar.YEAR, year)
                calDate.set(Calendar.MONTH, monthOfYear)
                calDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                deadline_date.setText(sdf.format(calDate.time))
            }

        deadline_deadline.setOnClickListener {
            DatePickerDialog(
                this@DeadlineEditActivity, dateSetListenerDeadline,
                calDed.get(Calendar.YEAR),
                calDed.get(Calendar.MONTH),
                calDed.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        deadline_date.setOnClickListener {
            DatePickerDialog(
                this@DeadlineEditActivity, dateSetListenerDate,
                calDate.get(Calendar.YEAR),
                calDate.get(Calendar.MONTH),
                calDate.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        refresh(rv_subplans)
///???
        add_subplan.setOnClickListener {
            var new_subplan: Subplan = Subplan()
            adapter?.notifyDataSetChanged()
            deadlineToEdit.subplans.add(new_subplan)
            adapter?.notifyDataSetChanged()
        }

        val spinner: Spinner = findViewById(R.id.categories_spinner)
        var adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, user.categories_names)
        println(user.categories_names.joinToString { e->e+" " }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun refresh(rvDeadlineList: RecyclerView) {
        adapter = DeadlineEditAdapter(deadlineToEdit.subplans)
        rvDeadlineList.adapter = adapter
        rvDeadlineList.layoutManager = LinearLayoutManager(this.applicationContext)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun save(view: View) {
        deadlineToEdit.title = deadline_title.text.toString()
        deadlineToEdit.category = Category(deadline_category.text.toString())
        deadlineToEdit.category = Category(categories_spinner.selectedItem.toString())
        deadlineToEdit.notes = deadline_notes.text.toString()
        deadlineToEdit.deadline = LocalDate.parse(
            deadline_deadline.text.toString(),
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
        )
        deadlineToEdit.date = LocalDate.parse(
            deadline_date.text.toString(),
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
        )
        deadlineToEdit.importance = deadline_importance.rating.toInt()
        deadlineToEdit.isFinished = deadline_finished.isChecked

        this.finish()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun delete(view: View) {
        userToEdit.plans.remove(deadlineToEdit)

        this.finish()
    }

    fun save() {
        val sharedPref = this.getSharedPreferences("User_saved", MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val data = userToEdit
            val gson = GsonBuilder().create()
            putString("User", gson.toJson(data))
            println("выходим " + gson.toJson(data))
            println("user to edit  " + userToEdit.toString())
            apply()
        }
    }

    override fun onPause() {
        save()
        super.onPause()
    }
}