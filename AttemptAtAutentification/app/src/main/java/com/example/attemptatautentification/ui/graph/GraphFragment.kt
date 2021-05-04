package com.example.attemptatautentification.ui.graph


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.attemptatautentification.R

class GraphFragment  : Fragment() {

  //  private lateinit var graphViewModel: GraphViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_graph, container, false)
//        graphViewModel =
//            ViewModelProvider(this).get(GraphViewModel::class.java)
//        val textView: TextView = root.findViewById(R.id.text_graph)
//        graphViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}