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
import com.example.attemptatautentification.ui.deadlineEdit.userToEdit
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

var userCalendar: User = User()

var parentActivityCalendar: BottomNavigationScreen = BottomNavigationScreen()


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
        }

        rvDeadlineList.addItemDecoration(divide)

        val button = root.findViewById<View>(R.id.addDeadlineCalendar) as Button
        button.setOnClickListener {
            var new_deadline: Plan = Plan()
            var ldate : LocalDate = calendar.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            new_deadline.date = ldate
            new_deadline.deadline = ldate
            adapter?.notifyDataSetChanged()
            openDeadlineScreen(new_deadline)
            userCalendar.plans.add(new_deadline)
            adapter?.notifyDataSetChanged()
            refresh(rvDeadlineList, calendar)
        }
        return root
    }

    fun openDeadlineScreen(deadline: Plan) {
        deadlineToEdit = deadline
        userToEdit =  userCalendar
        val intent = Intent(parentActivityCalendar, DeadlineEditActivity::class.java)
        intent.putExtra("deadline", deadline)
        parentActivityCalendar.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun refresh(rvDeadlineList :RecyclerView, calendar: Calendar) {
        var ldate : LocalDate = calendar.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        adapter = CalendarAdapter(userCalendar.plans.filter { it.date.equals(ldate) } as ArrayList<Plan>)

        rvDeadlineList.adapter = adapter
        rvDeadlineList.layoutManager = LinearLayoutManager(this.context)
    }

}