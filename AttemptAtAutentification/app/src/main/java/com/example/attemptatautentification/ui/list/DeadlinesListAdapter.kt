package com.example.attemptatautentification.ui.list

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.ui.deadlineEdit.DeadlineEditActivity
import com.example.attemptatautentification.R
import com.example.attemptatautentification.ui.deadlineEdit.deadlineToEdit
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.ui.deadlineEdit.userToEdit
import java.time.format.DateTimeFormatter
import java.util.*

class DeadlinesListAdapter(private val deadlines: ArrayList<Plan>, private val mode: String) :
        RecyclerView.Adapter<DeadlinesListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        var view: View
        if (mode == "light") {
            view =
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.deadline_item_light, parent, false)
            return ListViewHolder(view, "light")
        }

        view = LayoutInflater.from(parent.context).inflate(R.layout.deadline_item, parent, false)

        return ListViewHolder(view, "")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        println("size = ${deadlines.size}")
        return deadlines.size
    }

    private fun getItem(position: Int): Plan {
        return deadlines[position];
    }

     class ListViewHolder(itemView: View, private val mode: String) :
            RecyclerView.ViewHolder(itemView) {

        @RequiresApi(Build.VERSION_CODES.O)
         var plan: Plan = Plan()

        init {
            itemView.setOnClickListener {
                openDeadlineScreenEdit(plan)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
         fun bind(deadlineItem: Plan) {

            plan = deadlineItem
            val rating: RatingBar = itemView.findViewById(R.id.ratingBar)
            val name: TextView = itemView.findViewById(R.id.name)
            name.text = deadlineItem.title
            rating.rating = deadlineItem.importance.toFloat()

            val date: TextView = itemView.findViewById(R.id.date)
            val deadline: TextView = itemView.findViewById(R.id.deadline)
            var formattedDate = deadlineItem.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            var formattedDeadline =
                    deadlineItem.deadline.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))


            if (mode != "light") {
                val notes: TextView = itemView.findViewById(R.id.notes)
                val category: TextView = itemView.findViewById(R.id.category)
                val check: CheckBox = itemView.findViewById(R.id.checkBox)
                category.text = deadlineItem.category.name
                notes.text = deadlineItem.notes
                check.isChecked = deadlineItem.isFinished
                check.setOnClickListener({ deadlineItem.isFinished = check.isChecked })

                formattedDate = deadlineItem.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                formattedDeadline =
                        deadlineItem.deadline.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))

            }

            deadline.text = formattedDeadline
            date.text = formattedDate
        }


         fun openDeadlineScreenEdit(deadline: Plan) {
            deadlineToEdit = deadline
            userToEdit = userList
            val intent = Intent(parentActivityList, DeadlineEditActivity::class.java)
            intent.putExtra("deadline", deadline)
            parentActivityList.startActivity(intent)
        }
    }
}