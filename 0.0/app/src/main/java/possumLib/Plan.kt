package possumLib

class Plan(title_: String, isFinished_: Boolean = false, category_:String, importance_: Int = 1) {

    private var isFinished: Boolean
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

    private var title: String
        get() {
            return title
        }
        set(value) {
            if (value.length in 1..9) {
                title = value
            }
        }

    private var category: String
        get() {
            return category
        }
        set(value) {
            if (value.length in 1..9) {
                category = value
            }
        }

    private var importance : Int
    get(){return importance}
    set(value){if(value in 0..4){importance = value}}

    init {
        isFinished = isFinished_
        title = title_
        importance = importance_
        category = category_
    }
}
//дата, время срока выполнения;
//предполагаемое время, которое нужно для выполнения;
//подзадачи со следующими параметрами
//повторяемость дедлайна, с выбором из следующих значений
//не повторять;
//ежедневно;
//еженедельно;
//ежемесячно;
//произвольно;
//перенос дедлайна по истечении срока выполнения с выбором следующих опций:
//увеличить срок выполнения;
//отметить дедлайн как выполненный;
//ничего не совершать;
//напоминания со следующими опциями:
//не напоминать;
//напомнить за 3 часа до срока выполнения;
//напомнить за 12 часов до срока выполнения;
//напомнить за 1 день до срока исполнения;
//напомнить за 3 дня до срока выполнения;
//пользовательский выбор времени напоминания;
