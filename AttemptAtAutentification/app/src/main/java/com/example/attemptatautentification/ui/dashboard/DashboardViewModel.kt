package com.example.attemptatautentification.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "They don't call it a Quarter Pounder with cheese? Then what do they call it?"
    }
    val text: LiveData<String> = _text
}