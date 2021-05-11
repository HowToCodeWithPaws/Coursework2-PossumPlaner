package com.example.attemptatautentification.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.BottomNavigationScreen
import com.example.attemptatautentification.R
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.ui.list.ListAdapter

var userSettings: User = User()

fun setNewUserSettings(newUser: User) {
   userSettings = newUser
}

var parentActivitySettings: BottomNavigationScreen = BottomNavigationScreen()

fun setParentSettings(parent: BottomNavigationScreen) {
 parentActivitySettings = parent
}


class SettingsFragment : Fragment() {

    var adapter: SettingsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        val rvDeadlineList: RecyclerView = root.findViewById<RecyclerView>(R.id.rv_settings)
        var arr :ArrayList<String> = arrayListOf("категории","уведомления","учетная запись","интерфейс","дополнительные функции")
        adapter = SettingsAdapter(arr)
        rvDeadlineList.adapter = adapter
        rvDeadlineList.layoutManager = LinearLayoutManager(this.context)

        val divide: DividerItemDecoration = DividerItemDecoration(rvDeadlineList.context, DividerItemDecoration.HORIZONTAL)

        val mDivider  = this.context?.let { ContextCompat.getDrawable(it, R.drawable.divider_drawable) };
        if (mDivider != null) {
            divide.setDrawable(mDivider)
        }

        rvDeadlineList.addItemDecoration(divide)
        return root
    }
}