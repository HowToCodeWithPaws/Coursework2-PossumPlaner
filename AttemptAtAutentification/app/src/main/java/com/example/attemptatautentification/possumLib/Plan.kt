package com.example.attemptatautentification.possumLib

import java.util.*

enum class possibleRepetitions {
    NONE, DAILY, WEEKLY, MONTHLY ///TODO рандомно на выбор пользователя
}

enum class possiblePutOffs {
    NONE, NEXTDAY, NEXTWEEK, NEXTMONTH
}

enum class possibleReminders {
    NONE, H3BEFORE, H12BEFORE, H24BEFORE, H72BEFORE
}

class Plan(title_: String/*, isFinished_: Boolean = false,
           category_: Category, importance_: Int = 1,
           subplans_: ArrayList<Subplan> = ArrayList(),
           repetition_: possibleRepetitions = possibleRepetitions.NONE,
           putOff_: possiblePutOffs = possiblePutOffs.NONE,
           reminder_: possibleReminders = possibleReminders.NONE,
           time_: Double = 0.0,
           deadline_: Date = java.util.Calendar.getInstance().time,
           redline_: Date = java.util.Calendar.getInstance().time*/) {

     var isFinished: Boolean
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

     var title: String
        get() {
            return title
        }
        set(value) {
            if (value.length in 1..9) {
                title = value
            }
        }

     var category: Category
        get() {
            return category
        }
        set(value) {
            category = value
        }

     var importance: Int
        get() {
            return importance
        }
        set(value) {
            if (value in 0..4) {
                importance = value
            }
        }

     var repetition: possibleRepetitions
        get() {
            return repetition
        }
        set(value) {
            repetition = value
        }

     var subplans: ArrayList<Subplan>
        get() {
            return subplans
        }
        set(value) {
            subplans = value
        }

    fun addSubplan(subplan: Subplan) {
        subplans.add(subplan)
    }

    fun deleteSubplan(subplan: Subplan) {
        subplans.remove(subplan)
    }

     var putOff: possiblePutOffs
        get() {
            return putOff
        }
        set(value) {
            putOff = value
        }

     var reminder: possibleReminders
        get() {
            return reminder
        }
        set(value) {
            reminder = value
        }

     var time: Double
        get() {
            return time
        }
        set(value) {
            if (value >= 0) time = value
        }

     var deadline: Date
        get() {
            return deadline
        }
        set(value) {
            deadline = value
        }

     var redline: Date
        get() {
            return redline
        }
        set(value) {
            redline = value
        }

    init {
//        isFinished = isFinished_
        title = title_
//        importance = importance_
//        category = category_
//        repetition = repetition_
//        subplans = subplans_
//        putOff = putOff_
//        time = time_
//        deadline = deadline_
//        redline = redline_
    }
}

