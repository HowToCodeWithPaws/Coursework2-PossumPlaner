package com.example.attemptatautentification.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "(Because 7 is a prime number and prime numbers can be intimidating)"
    }
    val text: LiveData<String> = _text
}