package com.example.attemptatautentification.possumLib

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
///???
class Category constructor(name_: String = ""/*, colour_: Int = Color.BLACK*/) {
     var name: String
        get() {
            return name
        }
        set(value) {
            if (value.length in 1..9) {
                name = value
            }
        }

     var colour: Int
        get() {
            return colour
        }
        set(value) {
            colour = value
        }

    init {
        name = name_
        //colour = colour_
    }
}