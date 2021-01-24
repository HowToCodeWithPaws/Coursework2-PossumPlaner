package possumLib

import android.graphics.Color

class Category(name_: String = "", colour_: Color) {
    private var name: String
        get() {
            return name
        }
        set(value) {
            if (value.length in 1..9) {
                name = value
            }
        }

    private var colour: Color
        get() {
            return colour
        }
        set(value) {
            colour = value
        }

    init {
        name = name_
        colour = colour_
    }
}