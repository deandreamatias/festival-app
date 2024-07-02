package festival.deandreamatias.com.entity

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.char
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
data class Show(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("start_date")
    val startDate: String,
    @SerialName("start_time")
    val startTime: String,
    @SerialName("duration")
    val duration: String,
    @SerialName("genre")
    val genre: String = "",
    @SerialName("stage")
    val stage: String,
    @SerialName("enabledAlarm")
    val enabledAlarm: Boolean = false,
) {
    val startDateTime: LocalDateTime
        get() = LocalDateTime(localDate, localTime)

    private val localTime: LocalTime
        get() = LocalTime.parse(startTime)

    val localDate: LocalDate
        get() {
            val dateFormat = LocalDate.Format {
                dayOfMonth()
                char('/')
                monthNumber()
                char('/')
                year()
            }
            return LocalDate.parse(startDate, dateFormat)
        }

    private val durationTime: Duration
        get() = duration.split(":").let { Duration.parse("${it[0]}h ${it[1]}m") }

    val endTime: String
        get() {
            var seconds = localTime.toSecondOfDay() + durationTime.inWholeSeconds.toInt()
            if (seconds >= 86400) seconds -= 86400
            return LocalTime.fromSecondOfDay(seconds).toString()
        }

    val timeAlarm: MyTime get() = MyTime(id.toInt(), localTime.hour, localTime.minute)
}
