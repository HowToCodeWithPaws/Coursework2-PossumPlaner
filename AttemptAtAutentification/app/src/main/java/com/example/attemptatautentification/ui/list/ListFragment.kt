package com.example.attemptatautentification.ui.list

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
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.StringBuilder


class ListFragment : Fragment() {

    //  private lateinit var listViewModel: ListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)
//        listViewModel =
//            ViewModelProvider(this).get(ListViewModel::class.java)
//        val textView: TextView = root.findViewById(R.id.text_list)
//        listViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val button = root.findViewById<View>(R.id.addDeadlineList) as Button
        button.setOnClickListener {
            text_list.text =
                StringBuilder(text_list.text).append("\nдедлайн добавлен!\n").toString()
        }
        return root
    }
}