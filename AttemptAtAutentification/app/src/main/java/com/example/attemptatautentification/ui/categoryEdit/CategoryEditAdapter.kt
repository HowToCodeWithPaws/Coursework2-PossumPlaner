package com.example.attemptatautentification.ui.categoryEdit

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.ui.deadlineEdit.DeadlineEditActivity
import com.example.attemptatautentification.ui.deadlineEdit.deadlineToEdit
import com.example.attemptatautentification.ui.deadlineEdit.userToEdit
import com.example.attemptatautentification.ui.list.parentActivity
import com.example.attemptatautentification.ui.list.user
import java.time.format.DateTimeFormatter
import java.util.ArrayList

class CategoryEditAdapter(private val plans: ArrayList<Plan>) :
    RecyclerView.Adapter<CategoryEditAdapter.CategoryEditViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryEditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.deadline_item, parent, false)
        return CategoryEditViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CategoryEditViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        println("size = ${plans.size}")
        return plans.size
    }

    fun getItem(position: Int): Plan {
        return plans[position]
    }

    class CategoryEditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rating: RatingBar = itemView.findViewById(R.id.ratingBar)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val notes: TextView = itemView.findViewById(R.id.notes)
        private val category: TextView = itemView.findViewById(R.id.category)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val deadline: TextView = itemView.findViewById(R.id.deadline)
        private val check: CheckBox = itemView.findViewById(R.id.checkBox)


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
            category.text = deadlineItem.category.name
            name.text = deadlineItem.title
            notes.text = deadlineItem.notes
            date.text = deadlineItem.date.toString()
            deadline.text = deadlineItem.deadline.toString()
            rating.rating = deadlineItem.importance.toFloat()
            check.isChecked = deadlineItem.isFinished // todo удаление из списка?
            check.setOnClickListener({ deadlineItem.isFinished = check.isChecked })

            var formattedDate = deadlineItem.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            var formattedDeadline =
                deadlineItem.deadline.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            deadline.text = formattedDeadline
            date.text = formattedDate
        }


        fun openDeadlineScreenEdit(deadline: Plan) {
            deadlineToEdit = deadline
            userToEdit = user
            val intent = Intent(parentActivity, DeadlineEditActivity::class.java)
            intent.putExtra("deadline", deadline)
            parentActivity.startActivity(intent)
        }
    }
}