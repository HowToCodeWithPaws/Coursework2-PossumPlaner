package com.example.attemptatautentification.ui.list

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
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
      //  val deadline = getItem(position)
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        println("size = ${deadlines.size}")
        return deadlines.size
    }

    public fun  getItem(position:Int):Plan{
        return deadlines[position];
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rating : RatingBar = itemView.findViewById(R.id.ratingBar)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val notes: TextView = itemView.findViewById(R.id.notes)
        private val category: TextView = itemView.findViewById(R.id.category)
        private val redline: TextView = itemView.findViewById(R.id.redline)
        private val deadline: TextView = itemView.findViewById(R.id.deadline)
        private val check: TextView = itemView.findViewById(R.id.checkBox)
      //  private val ivPoster: ImageView = itemView.findViewById(R.id.movie_item__iv_poster)
      @RequiresApi(Build.VERSION_CODES.O)
      var plan :Plan = Plan()

        init {
            itemView.setOnClickListener {
openDeadlineScreenEdit(plan)
//                Toast.makeText(
//                    //openDeadlineScreenEdit(itemView.) todo find how to get the deadline from focus
//                        itemView.context,
//                        name.text,
//                        Toast.LENGTH_LONG
//                ).show()
            }
        }

        fun bind(deadlineItem: Plan) {
            plan = deadlineItem
            category.text = deadlineItem.category.name
            name.text = deadlineItem.title
            notes.text = deadlineItem.notes
            redline.text = deadlineItem.redline.toString()
            deadline.text = deadlineItem.deadline.toString()
        //    rating.numStars = deadlineItem.importance
            rating.rating = deadlineItem.importance.toFloat()
           // check. todo тыкательность и удаление из списка?
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