package com.example.attemptatautentification.possumLib

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
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
        set(value) {
            if (value == !field) {
                field = value
            }
        }

    //??
    fun changeFinished() {
        isFinished = !isFinished
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
            println("subs "+subplans.size)
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

    var date: LocalDateTime = LocalDateTime.now()
        get() {
            return field
        }
        set(value) {
            field = value
            println( "setting date to "+field.toString())
        }

    var deadline: LocalDateTime = LocalDateTime.now()
        get() {
            return field
        }
        set(value) {
            field = value
            println( "setting deadline to "+field.toString())
        }


    var notes: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    override fun toString(): String {
        return "category: " + category.name + "\n" + "importance " + importance.toString() + "\ndate: " + date.toString()+ "\ndeadline: " + deadline.toString() ;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isUrgent():Boolean{
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

