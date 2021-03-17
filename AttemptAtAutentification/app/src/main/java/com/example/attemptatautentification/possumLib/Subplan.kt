package com.example.attemptatautentification.possumLib

import java.io.Serializable

class Subplan(title_: String = "new subplan", isFinished_: Boolean = false) : Serializable {

     var isFinished: Boolean = false
        get() {
            return field
        }
        set(value) {
            if (value == !field) {
                field = value
            }
        }


    //??
    fun changeFinished() {
        isFinished = !isFinished
    }

     var title: String = "new subplan"
        get() {
            return field
        }
        set(value) {
            if (value.length in 1..9) {
                field = value
            }
        }

    init {
        isFinished = isFinished_
        title = title_
    }
}