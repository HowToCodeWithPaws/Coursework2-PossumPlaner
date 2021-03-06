package com.example.attemptatautentification.ui.graph

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GraphViewModel : ViewModel(){

    private val _text=MutableLiveData<String>().apply{
        value="there is cool visuals but there's not"
    }
    val text:LiveData<String> =_text
}
