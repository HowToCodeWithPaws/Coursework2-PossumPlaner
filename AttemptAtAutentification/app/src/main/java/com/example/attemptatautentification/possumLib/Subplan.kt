package com.example.attemptatautentification.possumLib

class Subplan(title_: String, isFinished_: Boolean = false) {

    private var isFinished: Boolean
        get() {
            return isFinished
        }
        set(value) {
            if (value == !isFinished) {
                isFinished = value
            }
        }

    fun changeFinished() {
        isFinished = !isFinished
    }

    private var title: String
        get() {
            return title
        }
        set(value) {
            if (value.length in 1..9) {
                title = value
            }
        }

    init {
        isFinished = isFinished_
        title = title_
    }
}