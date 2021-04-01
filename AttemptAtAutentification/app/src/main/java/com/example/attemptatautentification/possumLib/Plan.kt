package com.example.attemptatautentification.possumLib

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

enum class possibleRepetitions {
    NONE, DAILY, WEEKLY, MONTHLY ///TODO рандомно на выбор пользователя
}

enum class possiblePutOffs {
    NONE, NEXTDAY, NEXTWEEK, NEXTMONTH
}

enum class possibleReminders {
    NONE, H3BEFORE, H12BEFORE, H24BEFORE, H72BEFORE
}

class Plan(title_: String = "new plan", isFinished_: Boolean = false,
           category_: Category = Category(), importance_: Int = 1,
           subplans_: ArrayList<Subplan> = ArrayList(),
           repetition_: possibleRepetitions = possibleRepetitions.NONE,
           putOff_: possiblePutOffs = possiblePutOffs.NONE,
           reminder_: possibleReminders = possibleReminders.NONE,
           time_: Double = 0.0,
           deadline_: Date = java.util.Calendar.getInstance().time,
           redline_: Date = java.util.Calendar.getInstance().time): Serializable {

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

    var title: String = "new plan"
        get() {
            return field
        }
        set(value) {
            if (value.length in 1..19) {
                field = value
            }
        }

    var category: Category = Category()
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var importance: Int = 1
        get() {
            return field
        }
        set(value) {
            if (value in 0..4) {
                field = value
            }
        }

    var repetition: possibleRepetitions = possibleRepetitions.NONE
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var subplans: ArrayList<Subplan> = ArrayList()
        get() {
            return field
        }
        set(value) {
            field = value
        }

    fun addSubplan(subplan: Subplan) {
        subplans.add(subplan)
    }

    fun deleteSubplan(subplan: Subplan) {
        subplans.remove(subplan)
    }

    var putOff: possiblePutOffs = possiblePutOffs.NONE
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var reminder: possibleReminders = possibleReminders.NONE
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var time: Double = 0.0
        get() {
            return field
        }
        set(value) {
            if (value >= 0) field = value
        }

    var deadline: Date = java.util.Calendar.getInstance().time
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var redline: Date = java.util.Calendar.getInstance().time
        get() {
            return field
        }
        set(value) {
            field = value
        }

    override fun toString(): String {
        return "category: "+category.name+"\n"+"importance "+importance.toString()+"\n"+"пока все";
    }

    init {
        isFinished = isFinished_
        title = title_
        importance = importance_
        category = category_
        repetition = repetition_
        subplans = subplans_
        putOff = putOff_
        time = time_
        deadline = deadline_
        redline = redline_
    }
}

