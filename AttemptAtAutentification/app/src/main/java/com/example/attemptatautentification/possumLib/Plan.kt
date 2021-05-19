package com.example.attemptatautentification.possumLib



import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
enum class possibleRepetitions {
    NONE, DAILY, WEEKLY, MONTHLY, EACHYEAR
}

enum class possiblePutOffs {
    NONE, NEXTDAY, NEXTWEEK, NEXTMONTH, NEXTYEAR
}

enum class possibleReminders {
    NONE, H3BEFORE, H12BEFORE, H24BEFORE, H72BEFORE
}

class Plan @RequiresApi(Build.VERSION_CODES.O) constructor(title_: String = "новый план", isFinished_: Boolean = false,
                                                           category_: Category = Category(), notes_: String = "", importance_: Int = 1,
                                                           date_: LocalDateTime = LocalDateTime.now(),
                                                           deadline_: LocalDateTime = LocalDateTime.now(),
                                                           subplans_: ArrayList<Subplan> = ArrayList(),
                                                           repetition_: possibleRepetitions = possibleRepetitions.NONE,
                                                           putOff_: possiblePutOffs = possiblePutOffs.NONE,
                                                           reminder_: possibleReminders = possibleReminders.NONE,
                                                           time_: Double = 0.0) : Serializable {

    var isFinished: Boolean = false
        get() {
            return field
        }
        @RequiresApi(Build.VERSION_CODES.O)
        set(value) {
            if (value == !field) {
                field = value
                if (value) {
                    tryRepeat()
                }
            }

        }


    var title: String = "новый план"
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
            if (value in 0..5) {
                field = value
            }
        }

    fun setRepetition(value: String) {
        if (value == "каждый день") {
            repetition = possibleRepetitions.DAILY
        } else if (value == "каждую неделю") {
            repetition = possibleRepetitions.WEEKLY
        } else if (value == "каждый месяц") {
            repetition = possibleRepetitions.MONTHLY
        } else if (value == "каждый год") {
            repetition = possibleRepetitions.EACHYEAR
        } else {
            repetition = possibleRepetitions.NONE
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

//    fun addSubplan(subplan: Subplan) {
//        subplans.add(subplan)
//    }
//
//    fun deleteSubplan(subplan: Subplan) {
//        subplans.remove(subplan)
//    }

    fun setPutOff(value: String) {
        if (value == "на следующий день") {
            putOff = possiblePutOffs.NEXTDAY
        } else if (value == "на следующую неделю") {
            putOff = possiblePutOffs.NEXTWEEK
        } else if (value == "на следующий месяц") {
            putOff = possiblePutOffs.NEXTMONTH
        } else if (value == "на следующий год") {
            putOff = possiblePutOffs.NEXTYEAR
        } else {
            putOff = possiblePutOffs.NONE
        }
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

    var date: LocalDateTime = LocalDateTime.now()
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var deadline: LocalDateTime = LocalDateTime.now()
        get() {
            return field
        }
        set(value) {
            field = value
        }


    var notes: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isOverdue(): Boolean {
        return !isFinished && LocalDateTime.now().isAfter(date)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun tryPutOff() {
        if (isOverdue()) {
            if (putOff == possiblePutOffs.NEXTDAY) {
                date = date.plusDays(1)
            } else if (putOff == possiblePutOffs.NEXTWEEK) {
                date = date.plusWeeks(1)
            } else if (putOff == possiblePutOffs.NEXTMONTH) {
                date = date.plusMonths(1)
            } else if (putOff == possiblePutOffs.NEXTYEAR) {
                date = date.plusYears(1)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun tryRepeat() {
        if (repetition == possibleRepetitions.DAILY) {
            isFinished = false
            date = date.plusDays(1)
            deadline = deadline.plusDays(1)
        } else if (repetition == possibleRepetitions.WEEKLY) {
            isFinished = false
            date = date.plusWeeks(1)
            deadline = deadline.plusWeeks(1)
        } else if (repetition == possibleRepetitions.MONTHLY) {
            isFinished = false
            date = date.plusMonths(1)
            deadline = deadline.plusMonths(1)
        } else if (repetition == possibleRepetitions.EACHYEAR) {
            isFinished = false
            date = date.plusYears(1)
            deadline = deadline.plusYears(1)
        }
    }

    override fun toString(): String {
        return "category: " + category.name + "\n" + "importance " + importance.toString() + "\ndate: " + date.toString() + "\ndeadline: " + deadline.toString();
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isUrgent(): Boolean {
        return LocalDateTime.now().plusDays(2) >= deadline
    }

    init {
        isFinished = isFinished_
        title = title_
        notes = notes_
        importance = importance_
        category = category_
        repetition = repetition_
        reminder = reminder_
        subplans = subplans_
        putOff = putOff_
        time = time_
        date = date_
        deadline = deadline_
    }
}