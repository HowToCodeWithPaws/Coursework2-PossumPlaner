package possumLib

import java.util.ArrayList

class User(name_:String, token_: String, plans_: ArrayList<Plan> = ArrayList(), categories_:ArrayList<Category> = ArrayList()) {
    private var name: String
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

    private var plans: ArrayList<Plan>
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

    private var categories: ArrayList<Category>
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
        name = name_
        token = token_
        plans = plans_
        categories = categories_
    }
}