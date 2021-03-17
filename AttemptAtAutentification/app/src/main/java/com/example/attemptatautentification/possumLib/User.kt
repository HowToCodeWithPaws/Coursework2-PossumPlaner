package com.example.attemptatautentification.possumLib

import java.io.Serializable
import java.util.ArrayList

class User(name_:String/* = ""*/, token_: String /*= ""*/, plans_: ArrayList<Plan>/* = ArrayList()*/, categories_:ArrayList<Category> = ArrayList()):Serializable {
     var name: String
        get() {
            return name
        }
        set(value) {
            if (value.length in 1..9) {
                name = value
            }
        }

    private var token: String
        get() {
            return token
        }
        set(value) {
            if (value.length in 1..9) {
                token = value
            }
        }

     var plans: ArrayList<Plan>
        get() {
            return plans
        }
        set(value) {
            plans = value
        }

    fun addPlan(plan: Plan) {
        plans.add(plan)
    }

    fun deletePlan(plan: Plan) {
        plans.remove(plan)
    }

     var categories: ArrayList<Category>
        get() {
            return categories
        }
        set(value) {
            categories = value
        }

    fun addCategory(category: Category) {
        categories.add(category)
    }

    fun deletePlan(category: Category) {
        categories.remove(category)
    }

    init {
  //      name = name_
    //    token = token_
      //  plans = plans_
       // categories = categories_
    }
}