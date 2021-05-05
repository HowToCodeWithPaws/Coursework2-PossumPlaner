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
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.Subplan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.list.user
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
     //   deadline_category.setText(deadlineToEdit.category.name)
      //  categories_spinner.set
        deadline_notes.setText(deadlineToEdit.notes)
        deadline_finished.isChecked = deadlineToEdit.isFinished

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        var formattedDate = deadlineToEdit.date.format(formatter)
        var formattedDeadline = deadlineToEdit.deadline.format(formatter)
        deadline_deadline.setText(formattedDeadline)
              deadline_date.setText(formattedDate)
        var calDed = Calendar.getInstance()
        var calDate = Calendar.getInstance()

        val dateSetListenerDeadline = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calDed.set(Calendar.YEAR, year)
            calDed.set(Calendar.MONTH, monthOfYear)
            calDed.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            deadline_deadline.setText(sdf.format(calDed.time))
        }

        val dateSetListenerDate = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calDate.set(Calendar.YEAR, year)
            calDate.set(Calendar.MONTH, monthOfYear)
            calDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            deadline_date.setText(sdf.format(calDate.time))
        }

        deadline_deadline.setOnClickListener {
            DatePickerDialog(this@DeadlineEditActivity, dateSetListenerDeadline,
                    calDed.get(Calendar.YEAR),
                    calDed.get(Calendar.MONTH),
                    calDed.get(Calendar.DAY_OF_MONTH)).show()
        }

        deadline_date.setOnClickListener {
            DatePickerDialog(this@DeadlineEditActivity, dateSetListenerDate,
                    calDate.get(Calendar.YEAR),
                    calDate.get(Calendar.MONTH),
                    calDate.get(Calendar.DAY_OF_MONTH)).show()
        }

        refresh(rv_subplans)
///???
        add_subplan.setOnClickListener {
            var new_subplan: Subplan = Subplan()
            adapter?.notifyDataSetChanged()
            deadlineToEdit.subplans.add(new_subplan)
            //     adapter.refreshDrawableState()
            adapter?.notifyDataSetChanged()
        }


        val spinner: Spinner = findViewById(R.id.categories_spinner)
// Create an ArrayAdapter using the string array and a default spinner layout

       val names_c :  ArrayList<String> = arrayListOf()

//todo categories as?
       var adapter =  ArrayAdapter(this,android.R.layout.simple_spinner_item,user.categories_names)
            // Apply the adapter to the spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner

        spinner.adapter = adapter
        spinner.onItemSelectedListener = this


    }

    //class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback
        }
   // }

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
        deadlineToEdit.deadline = LocalDate.parse(deadline_deadline.text.toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        deadlineToEdit.date = LocalDate.parse(deadline_date.text.toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        deadlineToEdit.importance = deadline_importance.rating.toInt()
        deadlineToEdit.isFinished = deadline_finished.isChecked

        this.finish()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun delete(view: View) {
        userToEdit.plans.remove(deadlineToEdit)

        this.finish()
    }
}