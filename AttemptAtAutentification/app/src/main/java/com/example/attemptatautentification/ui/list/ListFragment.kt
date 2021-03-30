package com.example.attemptatautentification.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.User
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    //  private lateinit var listViewModel: ListViewModel
    private val deadlines = ArrayList<Plan>()

    var adapter: ListAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        if (savedInstanceState != null) {
            val new_user = savedInstanceState?.get("user_with_deadlines") as User
            print("HERE" + new_user.name + "\n" + new_user.plans[0].title + "\n" + new_user.plans[1].title)
        }
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        val cat = Category("cat")
        deadlines.add(Plan("test1", false, cat, 10))
        deadlines.add(Plan("test2", false, cat, 10))
        deadlines.add(Plan("test3", false, cat, 10))
//        val  view:View = inflater.inflate(R.layout.fragment_list, container, false)

        val rvDeadlineList: RecyclerView = root.findViewById<RecyclerView>(R.id.hello_activity__rv_movie_list)
        adapter = ListAdapter(deadlines)
        rvDeadlineList.adapter = adapter
        rvDeadlineList.layoutManager = LinearLayoutManager(this.context)

//        listViewModel =
//            ViewModelProvider(this).get(ListViewModel::class.java)
//        val textView: TextView = root.findViewById(R.id.text_list)
//        listViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        val button = root.findViewById<View>(R.id.addDeadlineList) as Button
        button.setOnClickListener {
            //    text_list.text =
            //          StringBuilder(text_list.text).append("\nдедлайн добавлен!\n").toString()

            deadlines.add(
                    Plan(
                            "plan${(deadlines.size + 1)}",
                            false, Category("new"), 5
                    )
            )
            adapter?.notifyDataSetChanged()

            /// TODO RECEIVE INFO
            //          if (arguments != null) {
//                val new_user: User = requireArguments().getSerializable("user_deadlines") as User
//                text_list.text =
//                        StringBuilder(text_list.text).append("\n" + new_user.name + "\n"/*+new_user.plans[0].category.name*/ + "\n" + new_user.plans[0].title + "\n").toString()
            //          } else {
            //         text_list.text =
            //               StringBuilder(text_list.text).append("\ngot nothing!\n").toString()

            //           text_list.text =
            //     StringBuilder(text_list.text).append("\n${category.name}\n${category2.name}").toString()
        }





        return root
    }
}