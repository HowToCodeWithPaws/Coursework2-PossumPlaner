package com.example.attemptatautentification.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.User
import kotlinx.android.synthetic.main.fragment_list.*


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

            /// TODO RECEIVE INFO
            if (arguments != null) {
//                val new_user: User = requireArguments().getSerializable("user_deadlines") as User
//                text_list.text =
//                        StringBuilder(text_list.text).append("\n" + new_user.name + "\n"/*+new_user.plans[0].category.name*/ + "\n" + new_user.plans[0].title + "\n").toString()
            } else {
                text_list.text =
                        StringBuilder(text_list.text).append("\ngot nothing!\n").toString()

                //           text_list.text =
                //     StringBuilder(text_list.text).append("\n${category.name}\n${category2.name}").toString()
            }
        }




        return root
    }
}