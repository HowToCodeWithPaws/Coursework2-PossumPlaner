package com.example.attemptatautentification

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import kotlinx.android.synthetic.main.activity_deadline_edit_screen.*
import kotlinx.android.synthetic.main.deadline_item.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
var deadlineToEdit: Plan = Plan()

class DeadlineEditActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deadline_edit_screen)

   //     val arguments = intent.extras
   //     deadline = arguments!!["deadline"] as Plan
        deadline_importance.rating = deadlineToEdit.importance.toFloat()
        deadline_title.setText(deadlineToEdit.title)
        deadline_category.setText(deadlineToEdit.category.name)
        deadline_notes.setText(deadlineToEdit.notes)
//        deadline_deadline.setText(deadlineToEdit.deadline.toString())
  //      deadline_date.setText(deadlineToEdit.date.toString())
        deadline_finished.isChecked = deadlineToEdit.isFinished

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        var formattedDate = deadlineToEdit.date.format(formatter)
        var formattedDeadline = deadlineToEdit.deadline.format(formatter)
        deadline_deadline.setText(formattedDeadline)
              deadline_date.setText(formattedDate)


   //     deadline_deadline.setText(SimpleDateFormat("dd.MM.yyyy", Locale.US).format(deadlineToEdit.deadline))
   //     deadline_date.setText(SimpleDateFormat("dd.MM.yyyy", Locale.US).format(deadlineToEdit.date))

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
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun save(view: View) {
        deadlineToEdit.title = deadline_title.text.toString()
        deadlineToEdit.category = Category(deadline_category.text.toString())
        deadlineToEdit.notes = deadline_notes.text.toString()
        deadlineToEdit.deadline = LocalDate.parse(deadline_deadline.text.toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        deadlineToEdit.date = LocalDate.parse(deadline_date.text.toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        deadlineToEdit.importance = deadline_importance.rating.toInt()
        deadlineToEdit.isFinished = deadline_finished.isChecked
        this.finish()
    }
}