package com.example.attemptatautentification.ui.deadlineEdit

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Subplan
import java.util.ArrayList

class DeadlineEditAdapter(private val sublpans: ArrayList<Subplan>) :
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

    private fun getItem(position: Int): Subplan {
        return sublpans[position]
    }

    class DeadlineEditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.subplan_name)
        private val check: CheckBox = itemView.findViewById(R.id.subplan_checkBox)


        @RequiresApi(Build.VERSION_CODES.O)
        private var subplan_note: Subplan = Subplan()


        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(subplan: Subplan) {
            subplan_note = subplan
            name.text = subplan.title

            check.isChecked = subplan.isFinished // todo удаление из списка?
            check.setOnClickListener({ subplan.isFinished = check.isChecked })

            name.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    subplan.title = name.text.toString()
                }

                override fun afterTextChanged(editable: Editable) {}
            })
        }
    }
}