package com.example.attemptatautentification.ui.deadlineEdit

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.MainActivity
import com.example.attemptatautentification.R
import com.example.attemptatautentification.passed_user
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.Subplan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.list.userList
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_deadline_edit_screen.*
import kotlinx.android.synthetic.main.deadline_item.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.O)
var deadlineToEdit: Plan = Plan()
var userToEdit: User = User()

class DeadlineEditActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var adapter: DeadlineEditAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_deadline_edit_screen)
        deadline_importance.rating = deadlineToEdit.importance.toFloat()
        deadline_title.setText(deadlineToEdit.title)
        deadline_notes.setText(deadlineToEdit.notes)
        deadline_finished.isChecked = deadlineToEdit.isFinished

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        var formattedDate = deadlineToEdit.date.format(formatter)
        var formattedDeadline = deadlineToEdit.deadline.format(formatter)

        deadline_deadline.setText(formattedDeadline)
        deadline_date.setText(formattedDate)
        deadline_deadline.setOnClickListener {
            showDateTimePicker(deadline_deadline)
        }

        deadline_date.setOnClickListener {
            showDateTimePicker(deadline_date)
        }

        refresh(rv_subplans)

        add_subplan.setOnClickListener {
            var new_subplan: Subplan = Subplan()
            adapter?.notifyDataSetChanged()
            deadlineToEdit.subplans.add(new_subplan)
            adapter?.notifyDataSetChanged()
        }

        val spinnerCat: Spinner = findViewById(R.id.categories_spinner)
        var adapterCat =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, userList.categories_names)
        adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCat.adapter = adapterCat
        spinnerCat.onItemSelectedListener = this

        val putoffs = arrayListOf<String>("без переноса", "на следующий день", "на следующую неделю", "на следующий месяц", "на следующий год")
        val spinnerPutOffs: Spinner = findViewById(R.id.putOff_spinner)
        var adapterPutOffs =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, putoffs)
        adapterPutOffs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPutOffs.adapter = adapterPutOffs
        spinnerPutOffs.onItemSelectedListener = this

        val repeat = arrayListOf<String>("не повторять", "каждый день", "каждую неделю", "каждый месяц", "каждый год")
        val spinnerRepeat: Spinner = findViewById(R.id.repeat_spinner)
        var adapterRepeat =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, repeat)
        adapterPutOffs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRepeat.adapter = adapterRepeat
        spinnerRepeat.onItemSelectedListener = this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDateTimePicker(textView: TextView) {
        var currentDate = Calendar.getInstance()
        val setListenerDate =
                DatePickerDialog.OnDateSetListener { viewDate, year, monthOfYear, dayOfMonth ->
                    currentDate.set(Calendar.YEAR, year)
                    currentDate.set(Calendar.MONTH, monthOfYear)
                    currentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val myFormat = "dd.MM.yyyy"
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    textView.setText(sdf.format(currentDate.time))

                    val setListenerTime = TimePickerDialog.OnTimeSetListener { viewTime, hourOfDay, minute ->
                        currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        currentDate.set(Calendar.MINUTE, minute)

                        var strHour: String = if (currentDate.get(Calendar.HOUR_OF_DAY) > 9) {
                            currentDate.get(Calendar.HOUR_OF_DAY).toString()
                        } else {
                            "0" + currentDate.get(Calendar.HOUR_OF_DAY).toString()
                        }

                        var strMin: String = if (currentDate.get(Calendar.MINUTE) > 9) {
                            currentDate.get(Calendar.MINUTE).toString()
                        } else {
                            "0" + currentDate.get(Calendar.MINUTE).toString()
                        }
                        textView.append(" " + strHour + ":" + strMin)
                    }
                    var tp = TimePickerDialog(this@DeadlineEditActivity, setListenerTime, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
                }
        var dialog = DatePickerDialog(this@DeadlineEditActivity, setListenerDate, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun refresh(rvDeadlineList: RecyclerView) {
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
        deadlineToEdit.setPutOff(putOff_spinner.selectedItem as String)
        deadlineToEdit.setRepetition(repeat_spinner.selectedItem as String)

        try {
            deadlineToEdit.deadline = LocalDateTime.parse(
                    deadline_deadline.text.toString(),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            )
        } catch (e: Exception) {
            deadlineToEdit.deadline = LocalDateTime.parse(
                    deadline_deadline.text.toString() + " 23:59",
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            )
        }

        try {
            deadlineToEdit.date = LocalDateTime.parse(
                    deadline_date.text.toString(),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            )
        } catch (e: Exception) {
            deadlineToEdit.date = LocalDateTime.parse(
                    deadline_date.text.toString() + " 23:59",
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            )
        }

        deadlineToEdit.importance = deadline_importance.rating.toInt()
        deadlineToEdit.isFinished = deadline_finished.isChecked

        MainActivity.dateManager.changePlan(deadlineToEdit)
        this.finish()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun delete(view: View) {
        userToEdit.plans.remove(deadlineToEdit)
        MainActivity.dateManager.deletePlan(deadlineToEdit)

        this.finish()
    }


    private fun save() {
        MainActivity.dateManager.upload(userToEdit)
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