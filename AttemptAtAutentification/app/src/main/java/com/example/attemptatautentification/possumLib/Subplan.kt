package com.example.attemptatautentification.possumLib

import java.io.Serializable

class Subplan(title_: String = "новый подплан", isFinished_: Boolean = false) : Serializable {

     var isFinished: Boolean = false
        get() {
            return field
        }
        set(value) {
            if (value == !field) {
                field = value
            }
        }

     var title: String = "новый подплан"
        get() {
            return field
        }
        set(value) {
            if (value.length in 1..19) {
                field = value
            }
        }

    init {
        isFinished = isFinished_
        title = title_
    }
}