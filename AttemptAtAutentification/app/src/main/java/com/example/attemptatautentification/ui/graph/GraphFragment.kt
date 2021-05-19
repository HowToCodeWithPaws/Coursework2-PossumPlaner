package com.example.attemptatautentification.ui.graph


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.BottomNavigationScreen
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.list.DeadlinesListAdapter
import java.util.ArrayList

var userGraph: User = User()
var parentActivityGraph: BottomNavigationScreen = BottomNavigationScreen()


class GraphFragment : Fragment() {

    private var adapterUI: DeadlinesListAdapter? = null
    private var adapterUNI: DeadlinesListAdapter? = null
    private var adapterNUI: DeadlinesListAdapter? = null
    private var adapterNUNI: DeadlinesListAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_graph, container, false)

        val info: Button = root.findViewById<Button>(R.id.info)
        info.setOnClickListener {
            Toast.makeText(parentActivityGraph.applicationContext, "Перед вами матрица Эйзенхауэра!\n" +
                    "Она делит ваши дела на категории в зависимости их срочности и важности." +
                    "\nДля каждой категории существует совет по работе с задачами:" +
                    "\nважное и срочное - сделать прямо сейчас" +
                    "\nважное и не срочное - сделать, как только появится время" +
                    "\nне важное и срочное - делегировать (если есть кому, конечно)" +
                    "\nне важное и не срочное - забыть об их существовании", Toast.LENGTH_LONG).show()
        }

        val rv_UI: RecyclerView = root.findViewById<RecyclerView>(R.id.rv_urgentImportant)
        adapterUI =
                DeadlinesListAdapter(userGraph.plans.filter { it.isUrgent() && it.importance > 2 && !it.isFinished } as ArrayList<Plan>, "light")
        rv_UI.adapter = adapterUI
        rv_UI.layoutManager = LinearLayoutManager(this.context)

        val rv_UNI: RecyclerView = root.findViewById<RecyclerView>(R.id.rv_urgentNotImportant)
        adapterUNI = DeadlinesListAdapter(userGraph.plans.filter { it.isUrgent() && it.importance <= 2 && !it.isFinished } as ArrayList<Plan>, "light")
        rv_UNI.adapter = adapterUNI
        rv_UNI.layoutManager = LinearLayoutManager(this.context)

        val rv_NUI: RecyclerView = root.findViewById<RecyclerView>(R.id.rv_notUrgentImportant)
        adapterNUI = DeadlinesListAdapter(userGraph.plans.filter { !it.isUrgent() && it.importance > 2 && !it.isFinished } as ArrayList<Plan>, "light")
        rv_NUI.adapter = adapterNUI
        rv_NUI.layoutManager = LinearLayoutManager(this.context)

        val rv_NUNI: RecyclerView = root.findViewById<RecyclerView>(R.id.rv_notUrgentNotImportant)
        adapterNUNI = DeadlinesListAdapter(userGraph.plans.filter { !it.isUrgent() && it.importance <= 2 && !it.isFinished } as ArrayList<Plan>, "light")
        rv_NUNI.adapter = adapterNUNI
        rv_NUNI.layoutManager = LinearLayoutManager(this.context)

        return root
    }
}