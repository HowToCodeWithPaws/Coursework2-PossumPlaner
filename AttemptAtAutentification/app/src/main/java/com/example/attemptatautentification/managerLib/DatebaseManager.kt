package com.example.attemptatautentification.managerLib

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.attemptatautentification.MainActivity
import com.example.attemptatautentification.possumLib.Category
import com.example.attemptatautentification.possumLib.Category.Companion.countId
import com.example.attemptatautentification.possumLib.Plan
import com.example.attemptatautentification.possumLib.Plan.Companion.counterId
import com.example.attemptatautentification.possumLib.User
import com.example.attemptatautentification.possumLib.possibleReminders
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.util.function.Consumer

class DatebaseManager(private val mainActivity: MainActivity) {
    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null
    fun okay(): Boolean {
        return ok
    }

    constructor(m: MainActivity, token: String?) : this(m) {
        if (ok) {
            try {
                ref = database!!.reference.child(token!!)
            } catch (ex: NullPointerException) {
                ok = false
            }
        }
    }

    fun changeCategory(category: Category) {
        ref!!.child("categories").child(category.colour.toString()).setValue(category.name)
    }

    fun changePlan(plan: Plan) {
        ref!!.child("plans").child(plan.id.toString()).child("isFinished").setValue(plan.isFinished)
        ref!!.child("plans").child(plan.id.toString()).child("title").setValue(plan.title)
        ref!!.child("plans").child(plan.id.toString()).child("notes").setValue(plan.notes)
        ref!!.child("plans").child(plan.id.toString()).child("importance").setValue(plan.importance)
        ref!!.child("plans").child(plan.id.toString()).child("category")
            .setValue(plan.category.colour)
        ref!!.child("plans").child(plan.id.toString()).child("repetition")
            .setValue(plan.repetition.toString())
        ref!!.child("plans").child(plan.id.toString()).child("reminder")
            .setValue(plan.reminder.toString())
        ref!!.child("plans").child(plan.id.toString()).child("putOff")
            .setValue(plan.putOff.toString())
        ref!!.child("plans").child(plan.id.toString()).child("time").setValue(plan.time)
        ref!!.child("plans").child(plan.id.toString()).child("date").setValue(plan.date)
        ref!!.child("plans").child(plan.id.toString()).child("deadline").setValue(plan.deadline)
        for (s in plan.subplans) {
            ref!!.child("plans").child(plan.id.toString()).child("subplans").child(s.title)
                .setValue(s.isFinished)
        }
    }

    fun deleteCategory(category: Category) {
        ref!!.child("categories").child(category.colour.toString()).removeValue()
    }

    fun deletePlan(plan: Plan) {
        ref!!.child("plans").child(plan.id.toString()).removeValue()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun getUser(_token: String?): User {
        if (database == null) {
            database = FirebaseDatabase.getInstance()
        }
        return try {
            ref = database!!.reference.child(_token!!)
            val t = ref!!.get()
            if (t.isCanceled) {
                mainActivity.exampleUser()
                return mainActivity.new_user
            }
            val res = t.result
            val user =
                User()
            user.token = _token
            user.name = res!!.child("name").getValue(String::class.java)!!
            res.child("categories").children
                .forEach(Consumer { c: DataSnapshot ->
                    val a =
                        Category()
                    var id  = try {
                        c.key!!.toInt()
                    } catch (ex: NumberFormatException) {
                        -1
                    }
                    if (id!= -1){
                        a.colour = id
                        a.name = c.getValue(String::class.java)!!
                        user.addCategory(a)
                    }

                })
            res.child("plans").children
                .forEach(Consumer { ds: DataSnapshot ->
                    val c = Plan()
                    c.title = ds.child("title").getValue(String::class.java)!!
                    c.date = ds.child("date")
                        .getValue(LocalDateTime::class.java)!!
                    c.deadline = ds.child("deadline")
                        .getValue(LocalDateTime::class.java)!!
                    c.isFinished =
                        ds.child("isFinished").getValue(Boolean::class.java)!!
                    c.id = ds.child("id").getValue(Int::class.java)!!
                    c.importance = ds.child("importance").getValue(Int::class.java)!!
                    c.notes = ds.child("notes").getValue(String::class.java)!!
                    c.setPutOff(
                        ds.child("putOff").getValue(String::class.java)!!
                    )
                    c.reminder = (ds.child("reminder").value as possibleReminders?)!!
                    c.setRepetition(
                        ds.child("repetition").getValue(String::class.java)!!
                    )
                    val tmp = ds.child("category").getValue(Int::class.java)!!
                    val ar =
                        user.categories.stream().filter { s: Category -> s.colour == tmp }
                            .toArray() as Array<Category>
                    if (ar.size > 0) {
                        c.category = ar[0]
                    }
                    user.addPlan(c)
                })
            user
        } catch (ex: NullPointerException) {
            mainActivity.exampleUser()
            mainActivity.new_user
        }
    }

    fun newUser(token: String?, name: String?): Boolean {
        try {
            database!!.reference.child(token!!).child("name").setValue(name)
            ref = database!!.reference.child(token)
            counterId = 0
            countId = 0
            return true
        } catch (ignored: NullPointerException) {
        }
        return false
    }

    fun upload(user: User): Boolean {
        try {
            ref = database!!.reference.child(user.token)
            ref!!.child("name").setValue(user.name)
            for (c in user.categories) {
                changeCategory(c)
            }
            for (p in user.plans) {
                changePlan(p)
            }
        } catch (ex: Exception) {
            return false
        }
        return true
    }

    companion object {
        private var ok = true
    }

    init {
        try {
            database = FirebaseDatabase.getInstance()
        } catch (ex: NullPointerException) {
            ok = false
        }
    }
}