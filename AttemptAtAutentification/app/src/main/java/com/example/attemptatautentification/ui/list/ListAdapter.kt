package com.example.attemptatautentification.ui.list

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.DeadlineEditActivity
import com.example.attemptatautentification.R
import com.example.attemptatautentification.deadlineToEdit
import com.example.attemptatautentification.possumLib.Plan
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter(private val deadlines: ArrayList<Plan>) :
        RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.deadline_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        println("size = ${deadlines.size}")
        return deadlines.size
    }

     fun getItem(position: Int): Plan {
        return deadlines[position];
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rating: RatingBar = itemView.findViewById(R.id.ratingBar)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val notes: TextView = itemView.findViewById(R.id.notes)
        private val category: TextView = itemView.findViewById(R.id.category)
        private val redline: TextView = itemView.findViewById(R.id.redline)
        private val deadline: TextView = itemView.findViewById(R.id.deadline)
        private val check: CheckBox = itemView.findViewById(R.id.checkBox)



        @RequiresApi(Build.VERSION_CODES.O)
        var plan: Plan = Plan()

        init {
            itemView.setOnClickListener {
                openDeadlineScreenEdit(plan)
            }
        }

        fun bind(deadlineItem: Plan) {
            plan = deadlineItem
            category.text = deadlineItem.category.name
            name.text = deadlineItem.title
            notes.text = deadlineItem.notes
            redline.text = deadlineItem.redline.toString()
            deadline.text = deadlineItem.deadline.toString()
            rating.rating = deadlineItem.importance.toFloat()
            check.isChecked = deadlineItem.isFinished // todo удаление из списка?
            check.setOnClickListener({deadlineItem.isFinished = check.isChecked})
            redline.setText(SimpleDateFormat("dd.MM.yyyy", Locale.US).format(System.currentTimeMillis()))
            deadline.setText(SimpleDateFormat("dd.MM.yyyy", Locale.US).format(System.currentTimeMillis()))
        }


        fun openDeadlineScreenEdit(deadline: Plan) {
            deadlineToEdit = deadline
            val intent = Intent(parentActivity, DeadlineEditActivity::class.java)
            intent.putExtra("deadline", deadline)
            parentActivity.startActivity(intent)
        }
    }

    fun changeFinished(view: View){}

}