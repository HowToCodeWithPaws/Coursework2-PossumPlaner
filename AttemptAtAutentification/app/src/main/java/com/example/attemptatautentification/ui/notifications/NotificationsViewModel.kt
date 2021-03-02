package com.example.attemptatautentification.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "They call it a Royale with cheese."
    }
    val text: LiveData<String> = _text
}