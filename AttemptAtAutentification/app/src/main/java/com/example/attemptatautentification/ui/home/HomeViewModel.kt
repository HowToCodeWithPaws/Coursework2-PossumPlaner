package com.example.attemptatautentification.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Why was 6 afraid of 7?"
    }
    val text: LiveData<String> = _text
}