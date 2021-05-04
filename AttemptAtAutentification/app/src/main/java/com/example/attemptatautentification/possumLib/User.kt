package com.example.attemptatautentification.possumLib

import java.io.Serializable
import java.util.ArrayList

class User(name_: String = "new user", token_: String = "token", plans_: ArrayList<Plan> = ArrayList(), categories_: ArrayList<Category> = ArrayList()) : Serializable {
    var name: String = "new user"
        get() {
            return field
        }
        set(value) {
            if (value.length in 1..9) {
                field = value
            }
        }

    private var token: String = "token"
        get() {
            return field
        }
        set(value) {
            if (value.length in 1..9) {
                field = value
            }
        }

    var plans: ArrayList<Plan> = ArrayList()
        get() {
            return field
        }
        set(value) {
            field = value
        }

    fun addPlan(plan: Plan) {
        plans.add(plan)
    }

    fun deletePlan(plan: Plan) {
        plans.remove(plan)
    }

    var categories: ArrayList<Category> = ArrayList()
        get() {
            return field
        }
        set(value) {
            field = value

            for(cat : Category in value){
                categories_names.add(cat.name)
            }
        }

    var categories_names: ArrayList<String> = ArrayList()
        get() {
            return field
        }
        set(value) {
            field = value
        }

    fun addCategory(category: Category) {
        categories.add(category)
    }

    fun deletePlan(category: Category) {
        categories.remove(category)
    }

    init {
        name = name_
        token = token_
        plans = plans_
        categories = categories_

        categories_names.add("без категории")
    }
}