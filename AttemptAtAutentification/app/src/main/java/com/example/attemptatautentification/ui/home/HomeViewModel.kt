package com.example.attemptatautentification.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "And you know what they call a Quarter Pounder with Cheese in Paris?"
    }
    val text: LiveData<String> = _text
}