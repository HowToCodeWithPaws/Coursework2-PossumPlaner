package possumLib

class Goal() {
    private var isFinished: Boolean
        get() {
            return isFinished
        }
        set(value) {
            if (value == !isFinished) {
                isFinished = value
            }
        }

    fun changeDone() {
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

    constructor(name: String, done: Boolean = false) : this() {
        isFinished = done
        title = name
    }
}