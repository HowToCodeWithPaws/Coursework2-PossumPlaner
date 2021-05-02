package com.example.attemptatautentification.ui.calendar

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.BottomNavigationScreen
import com.example.attemptatautentification.ui.deadlineEdit.DeadlineEditActivity
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.deadlineEdit.deadlineToEdit
import com.example.attemptatautentification.ui.list.ListAdapter
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.StringBuilder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

var user: User = User()

fun setNewUser(newUser: User) {
    user = newUser
}

var parentActivity: BottomNavigationScreen = BottomNavigationScreen()

fun setParent(parent: BottomNavigationScreen) {
    parentActivity = parent
}

class CalendarFragment : Fragment() {
    var adapter: CalendarAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)

        val rvDeadlineList: RecyclerView = root.findViewById<RecyclerView>(R.id.rv_list_calendar)
        val calendarView = root.findViewById<View>(R.id.calendarView) as CalendarView
        val calendar = Calendar.getInstance()
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            calendarView.date = calendar.timeInMillis
            refresh(rvDeadlineList, calendar)
        }

        refresh(rvDeadlineList, calendar)

        val divide: DividerItemDecoration =
            DividerItemDecoration(rvDeadlineList.context, DividerItemDecoration.HORIZONTAL)

        val mDivider =
            this.context?.let { ContextCompat.getDrawable(it, R.drawable.divider_drawable) };
        if (mDivider != null) {
            divide.setDrawable(mDivider)
        };

        rvDeadlineList.addItemDecoration(divide)

        val button = root.findViewById<View>(R.id.addDeadlineCalendar) as Button
        button.setOnClickListener {
            var new_deadline: Plan = Plan()
            var ldate : LocalDate = calendar.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            new_deadline.date = ldate
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
        val intent = Intent(parentActivity, DeadlineEditActivity::class.java)
        intent.putExtra("deadline", deadline)
        parentActivity.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun refresh(rvDeadlineList :RecyclerView, calendar: Calendar) {
        var ldate : LocalDate = calendar.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        adapter = CalendarAdapter(user.plans.filter { it.date.equals(ldate) } as ArrayList<Plan>)

        rvDeadlineList.adapter = adapter
        rvDeadlineList.layoutManager = LinearLayoutManager(this.context)
    }

}