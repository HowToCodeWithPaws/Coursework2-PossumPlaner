package com.example.attemptatautentification.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.databinding.ListItemBinding
import com.example.attemptatautentification.possumLib.Plan
import java.util.ArrayList


class NestedListAdapter(
        private var listList: List<ListForRV>
) : RecyclerView.Adapter<NestedListAdapter.ViewHolder>() {

    var adapter: DeadlinesListAdapter? = null

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listList[position]) {
                binding.tvListName.text = this.name
                if (listList[position].name == "Текущие дела") {
                    adapter =
                            DeadlinesListAdapter(userList.plans.filter { it.isFinished == false } as ArrayList<Plan>,
                                    "")
                } else {
                    adapter =
                            DeadlinesListAdapter(userList.plans.filter { it.isFinished == true } as ArrayList<Plan>,
                                    "")
                }
                binding.deadlinesList.adapter = adapter

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