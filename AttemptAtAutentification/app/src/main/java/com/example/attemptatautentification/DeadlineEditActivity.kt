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
        deadline_deadline.setText(deadlineToEdit.deadline.toString())
        deadline_redline.setText(deadlineToEdit.redline.toString())
        deadline_finished.isChecked = deadlineToEdit.isFinished

        deadline_deadline.setText(SimpleDateFormat("dd.MM.yyyy", Locale.US).format(System.currentTimeMillis()))
        deadline_redline.setText(SimpleDateFormat("dd.MM.yyyy", Locale.US).format(System.currentTimeMillis()))

        var calDed = Calendar.getInstance()
        var calRed = Calendar.getInstance()

        val dateSetListenerDeadline = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calDed.set(Calendar.YEAR, year)
            calDed.set(Calendar.MONTH, monthOfYear)
            calDed.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            deadline_deadline.setText(sdf.format(calDed.time))
        }

        val dateSetListenerRedline = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calRed.set(Calendar.YEAR, year)
            calRed.set(Calendar.MONTH, monthOfYear)
            calRed.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            deadline_redline.setText(sdf.format(calRed.time))
        }

        deadline_deadline.setOnClickListener {
            DatePickerDialog(this@DeadlineEditActivity, dateSetListenerDeadline,
                    calDed.get(Calendar.YEAR),
                    calDed.get(Calendar.MONTH),
                    calDed.get(Calendar.DAY_OF_MONTH)).show()
        }

        deadline_redline.setOnClickListener {
            DatePickerDialog(this@DeadlineEditActivity, dateSetListenerRedline,
                    calRed.get(Calendar.YEAR),
                    calRed.get(Calendar.MONTH),
                    calRed.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun save(view: View) {
        deadlineToEdit.title = deadline_title.text.toString()
        deadlineToEdit.category = Category(deadline_category.text.toString())
        deadlineToEdit.notes = deadline_notes.text.toString()
        deadlineToEdit.deadline = LocalDate.parse(deadline_deadline.text.toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        deadlineToEdit.redline = LocalDate.parse(deadline_redline.text.toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        deadlineToEdit.importance = deadline_importance.rating.toInt()
        deadlineToEdit.isFinished = deadline_finished.isChecked
        this.finish()
    }
}