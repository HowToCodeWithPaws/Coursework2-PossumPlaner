package com.example.attemptatautentification.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.attemptatautentification.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.StringBuilder

class CalendarFragment : Fragment() {

   // private lateinit var calendarViewModel: CalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
//        calendarViewModel =
//            ViewModelProvider(this).get(CalendarViewModel::class.java)
//        val textView: TextView = root.findViewById(R.id.text_calendar)
//        calendarViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        val button = root.findViewById<View>(R.id.addDeadlineCalendar) as Button
        button.setOnClickListener {
            text_calendar.text =
                StringBuilder(text_calendar.text).append("\nдедлайн добавлен!\n").toString()
        }
        return root
    }
}