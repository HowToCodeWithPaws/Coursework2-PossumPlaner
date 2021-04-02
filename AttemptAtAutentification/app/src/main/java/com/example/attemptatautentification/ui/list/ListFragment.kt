package com.example.attemptatautentification.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.BottomNavigationScreen
import com.example.attemptatautentification.DeadlineEditActivity
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.User

var user: User = User()

fun setNewUser(newUser: User) {
    user = newUser
}

var parentActivity : BottomNavigationScreen = BottomNavigationScreen()

fun setParent(parent:BottomNavigationScreen){
    parentActivity = parent
}

class ListFragment : Fragment() {

    var adapter: ListAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //    print("HERE" + user.name + "\n" + user.plans[0].title + "\n" + user.plans[1].title)

        val root = inflater.inflate(R.layout.fragment_list, container, false)

        val rvDeadlineList: RecyclerView = root.findViewById<RecyclerView>(R.id.hello_activity__rv_movie_list)
        adapter = ListAdapter(user.plans)
        rvDeadlineList.adapter = adapter
        rvDeadlineList.layoutManager = LinearLayoutManager(this.context)

        val button = root.findViewById<View>(R.id.addDeadlineList) as Button
        button.setOnClickListener {
            var new_deadline :Plan =  Plan(
                    "plan${(user.plans.size + 1)}",
                    false, Category("new"), 5
            )
            //todo save info from screen, make editable
            openDeadlineScreen(new_deadline)
            user.plans.add(new_deadline)
            adapter?.notifyDataSetChanged()
        }
        return root
    }

    fun openDeadlineScreen(deadline:Plan){
        val intent = Intent(parentActivity, DeadlineEditActivity::class.java)
        intent.putExtra("deadline", deadline)
       parentActivity.startActivity(intent)
    }
}