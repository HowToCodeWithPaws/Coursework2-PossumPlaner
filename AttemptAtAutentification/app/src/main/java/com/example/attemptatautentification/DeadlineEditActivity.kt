package com.example.attemptatautentification

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import kotlinx.android.synthetic.main.activity_deadline_edit_screen.*
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
var deadlineToEdit: Plan = Plan()

class DeadlineEditActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deadline_edit_screen)

   //     val arguments = intent.extras
   //     deadline = arguments!!["deadline"] as Plan
        deadline_title.setText(deadlineToEdit.title)
        deadline_category.setText(deadlineToEdit.category.name)
        deadline_importance.setText(deadlineToEdit.importance.toString())
        deadline_deadline.setText(deadlineToEdit.deadline.toString())
        deadline_redline.setText(deadlineToEdit.redline.toString())

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun save(view: View) {
        deadlineToEdit.title = deadline_title.text.toString()
        deadlineToEdit.category = Category(deadline_category.text.toString())
        deadlineToEdit.importance = deadline_importance.text.toString().toInt()
        deadlineToEdit.deadline = LocalDate.parse(deadline_deadline.text.toString())
        deadlineToEdit.redline = LocalDate.parse(deadline_redline.text.toString())
        this.finish()
    }
}