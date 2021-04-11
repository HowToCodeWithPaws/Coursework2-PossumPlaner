package com.example.attemptatautentification.ui.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.DeadlineEditActivity
import com.example.attemptatautentification.R
import com.example.attemptatautentification.deadlineToEdit
import com.example.attemptatautentification.possumLib.Plan
import java.util.ArrayList

class ListAdapter(private val deadlines: ArrayList<Plan>) :
        RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.deadline_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val deadline = deadlines[position]
        holder.bind(deadline)
    }

    override fun getItemCount(): Int {
        println("Movies size = ${deadlines.size}")
        return deadlines.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.movie_item__tv_name)
        private val description: TextView = itemView.findViewById(R.id.movie_item__tv_description)
      //  private val ivPoster: ImageView = itemView.findViewById(R.id.movie_item__iv_poster)

        init {
            itemView.setOnClickListener {
                Toast.makeText(
                    //openDeadlineScreenEdit(itemView.) todo find how to get the deadline from focus
                        itemView.context,
                        name.text,
                        Toast.LENGTH_LONG
                ).show()
            }
        }

        fun bind(deadline: Plan) {
            name.text = deadline.title
            description.text =deadline.toString()
     //       ivPoster.setImageResource(deadline.picture)
        }


        fun openDeadlineScreenEdit(deadline:Plan){
            deadlineToEdit = deadline
            val intent = Intent(parentActivity, DeadlineEditActivity::class.java)
            intent.putExtra("deadline", deadline)
            parentActivity.startActivity(intent)
        }
    }


}