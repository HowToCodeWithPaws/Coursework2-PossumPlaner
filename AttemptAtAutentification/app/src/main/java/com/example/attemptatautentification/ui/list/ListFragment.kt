package com.example.attemptatautentification.ui.list

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.BottomNavigationScreen
import com.example.attemptatautentification.ui.deadlineEdit.DeadlineEditActivity
import com.example.attemptatautentification.R
import com.example.attemptatautentification.ui.deadlineEdit.deadlineToEdit
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.deadlineEdit.userToEdit
import kotlinx.android.synthetic.main.activity_main.*

var user: User = User()

fun setNewUser(newUser: User) {
    user = newUser
}

var parentActivity: BottomNavigationScreen = BottomNavigationScreen()

fun setParent(parent: BottomNavigationScreen) {
    parentActivity = parent
}

class ListFragment : Fragment() {

    var adapter: ListAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_list, container, false)

        val rvDeadlineList: RecyclerView = root.findViewById<RecyclerView>(R.id.rv_list)
        adapter = ListAdapter(user.plans)
        rvDeadlineList.adapter = adapter
        rvDeadlineList.layoutManager = LinearLayoutManager(this.context)

        val divide: DividerItemDecoration = DividerItemDecoration(rvDeadlineList.context, DividerItemDecoration.HORIZONTAL)

        val mDivider  = this.context?.let { ContextCompat.getDrawable(it, R.drawable.divider_drawable) };
        if (mDivider != null) {
            divide.setDrawable(mDivider)
        };


        rvDeadlineList.addItemDecoration(divide)
        val button = root.findViewById<View>(R.id.addDeadlineList) as Button
        button.setOnClickListener {
            var new_deadline: Plan = Plan()
            //todo refresh
            openDeadlineScreen(new_deadline)
            adapter?.notifyDataSetChanged()
            user.plans.add(new_deadline)
           //     adapter.refreshDrawableState()
            adapter?.notifyDataSetChanged()
        }
        return root
    }

    fun openDeadlineScreen(deadline: Plan) {
        deadlineToEdit = deadline
        userToEdit = user
        val intent = Intent(parentActivity, DeadlineEditActivity::class.java)
        intent.putExtra("deadline", deadline)
        parentActivity.startActivity(intent)
    }

}