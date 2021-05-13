package com.example.attemptatautentification.ui.list

import android.R
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attemptatautentification.BottomNavigationScreen
import com.example.attemptatautentification.ui.deadlineEdit.DeadlineEditActivity
import com.example.attemptatautentification.databinding.FragmentListBinding
import com.example.attemptatautentification.ui.deadlineEdit.deadlineToEdit
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.deadlineEdit.userToEdit

var userList: User = User()
var parentActivityList: BottomNavigationScreen = BottomNavigationScreen()

class ListFragment : Fragment(),  AdapterView.OnItemSelectedListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private var listList = ArrayList<ListForRV>()
    private lateinit var nestedListAdapter: NestedListAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        _binding = FragmentListBinding.inflate(layoutInflater)

        binding.addDeadlineList.setOnClickListener {
            var new_deadline: Plan = Plan()
            //todo refresh
            openDeadlineScreen(new_deadline)
            nestedListAdapter?.notifyDataSetChanged()
            userList.plans.add(new_deadline)
            nestedListAdapter?.notifyDataSetChanged()
        }

        var sorts = arrayListOf<String>("по срочности", "по важности")

        var adapter =
                ArrayAdapter(parentActivityList.applicationContext, R.layout.simple_spinner_item, sorts)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.sortSpinner.adapter = adapter
        binding.sortSpinner.onItemSelectedListener = this

        refresh("по срочности")

        return binding.root
    }

    fun openDeadlineScreen(deadline: Plan) {
        deadlineToEdit = deadline
        userToEdit = userList
        val intent = Intent(parentActivityList, DeadlineEditActivity::class.java)
        intent.putExtra("deadline", deadline)
        parentActivityList.startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        refresh(binding.sortSpinner.selectedItem as String)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        refresh("по срочности")
    }

    fun refresh(sort:String){
        listList = ArrayList()
        binding.rvList.layoutManager = LinearLayoutManager(parentActivityList.applicationContext)

        nestedListAdapter = NestedListAdapter(listList)
        binding.rvList.adapter = nestedListAdapter

        val listCurrent = ListForRV(
                "Текущие дела", true, sort
        )
        val listFinished = ListForRV(
                "Завершенные дела", false,  sort
        )

        listList.add(listCurrent)
        listList.add(listFinished)

        nestedListAdapter.notifyDataSetChanged()
    }
}