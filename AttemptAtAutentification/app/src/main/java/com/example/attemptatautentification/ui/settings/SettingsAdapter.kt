package com.example.attemptatautentification.ui.settings

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.attemptatautentification.R
import com.example.attemptatautentification.ui.settings.categories.SettingsCategoriesScreen
import com.example.attemptatautentification.ui.settings.categories.userWithCategories
import java.util.ArrayList

class SettingsAdapter(private val settings: ArrayList<String>) :
    RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingsAdapter.SettingsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.settings_item, parent, false)
        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsAdapter.SettingsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        println("size = ${settings.size}")
        return settings.size
    }

    fun getItem(position: Int): String {
        return settings[position];
    }

    class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.settings_name)

        init {
            itemView.setOnClickListener {
                openSettingsScreen(name.text.toString())
            }
        }

        fun bind(settingsItem: String) {
            name.text = settingsItem
        }


        fun openSettingsScreen(name: String) {
            if (name=="категории") {
                userWithCategories = userSettings
                val intent = Intent(parentActivitySettings, SettingsCategoriesScreen::class.java)
                parentActivitySettings.startActivity(intent)
            }else if(name=="учетная запись"){
                val intent = Intent(parentActivitySettings, UserSettingsActivity::class.java)
                parentActivitySettings.startActivity(intent)
            }
            ///todo остальные
        }
    }
}