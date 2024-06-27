package festival.deandreamatias.com.ui

import kotlinx.datetime.*

fun LocalDate.getRelativeDay(): String {
    val today = getLocalDateNow()
    return when (this) {
        today.minus(DatePeriod(days = 1)) -> "Yesterday"
        today -> "Today"
        today.plus(DatePeriod(days = 1)) -> "Tomorrow"
        else -> this.toString()
    }
}

/// TODO: Replace this to use the actual current time
/// Only use this for testing purposes
fun getLocalDateNow(): LocalDate = LocalDate(2024, 7, 27)
fun getLocalDateTimeNow(): LocalDateTime =
    LocalDateTime(getLocalDateNow(), LocalTime(12, 0))
//fun getLocalDateNow(): LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
//fun getLocalDateTimeNow(): LocalDateTime =
//    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())