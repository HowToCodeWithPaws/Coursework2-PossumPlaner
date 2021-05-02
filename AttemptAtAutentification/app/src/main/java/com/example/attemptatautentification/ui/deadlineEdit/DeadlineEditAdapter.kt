package com.example.attemptatautentification.ui.deadlineEdit

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
import com.example.attemptatautentification.possumLib.Subplan
import com.example.attemptatautentification.ui.calendar.CalendarAdapter
import com.example.attemptatautentification.ui.calendar.parentActivity
import java.time.format.DateTimeFormatter
import java.util.ArrayList

class DeadlineEditAdapter(private val sublpans: ArrayList<Subplan>)  :
        RecyclerView.Adapter<DeadlineEditAdapter.DeadlineEditViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeadlineEditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subplan_item, parent, false)
        return DeadlineEditViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DeadlineEditViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        println("size = ${sublpans.size}")
        return sublpans.size
    }

    fun getItem(position: Int): Subplan {
        return sublpans[position]
    }

    class DeadlineEditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.subplan_name)
        private val check: CheckBox = itemView.findViewById(R.id.subplan_checkBox)


        @RequiresApi(Build.VERSION_CODES.O)
        var subplan_note: Subplan = Subplan()

// TODO: 02.05.2021
//        init {
//            itemView.setOnClickListener {
//                openDeadlineScreenEdit(plan)
//            }
//        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(subplan: Subplan) {
            subplan_note = subplan
            name.text = subplan.title
            check.isChecked = subplan.isFinished // todo удаление из списка?
            check.setOnClickListener({subplan.isFinished = check.isChecked})
        }


//        fun openDeadlineScreenEdit(deadline: Plan) {
//            deadlineToEdit = deadline
//            val intent = Intent(parentActivity, DeadlineEditActivity::class.java)
//            intent.putExtra("deadline", deadline)
//            parentActivity.startActivity(intent)
//        }
    }
}