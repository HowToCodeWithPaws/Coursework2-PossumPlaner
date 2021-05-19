package com.example.attemptatautentification.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.R
import com.example.attemptatautentification.databinding.ListItemBinding
import com.example.attemptatautentification.possumLib.Plan
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.ArrayList


class NestedListAdapter(
        private var listList: List<ListForRV>
) : RecyclerView.Adapter<NestedListAdapter.ViewHolder>() {

    private var thisAdapter: NestedListAdapter? = null
    private var adapter: DeadlinesListAdapter? = null

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        thisAdapter = this
        with(holder) {
            with(listList[position]) {
                binding.tvListName.text = this.name
                if (listList[position].name == "Текущие дела") {
                    var array = userList.plans.filter { !it.isFinished } as ArrayList<Plan>
                    if (this.sort == "по важности") {
                        array.sortByDescending { it.importance }
                    } else {
                        array.sortBy { it.deadline }
                    }
                    adapter =
                            DeadlinesListAdapter(array,
                                    "")
                } else {
                    var array = userList.plans.filter { it.isFinished } as ArrayList<Plan>
                    if (this.sort == "по важности") {
                        array.sortByDescending { it.importance }
                    } else {
                        array.sortBy { it.deadline }
                    }
                    adapter =
                            DeadlinesListAdapter(array,
                                    "")
                }
                binding.deadlinesList.adapter = adapter

                binding.eyeOpen.visibility = if (this.expand) {
                    ImageView.VISIBLE
                } else {
                    ImageView.INVISIBLE
                }

                binding.eyeClosed.visibility = if (!this.expand) {
                    ImageView.VISIBLE
                } else {
                    ImageView.INVISIBLE
                }

                binding.expandedView.visibility = if (this.expand) View.VISIBLE else View.GONE
                binding.cardLayout.setOnClickListener {
                    this.expand = !this.expand
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listList.size
    }
}