package festival.deandreamatias.com.entity

import kotlinx.datetime.LocalTime
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
    val genre: String,
    @SerialName("stage")
    val stage: String,
) {
    val startDateTime: String
        get() = "$startDate $startTime"

    val durationTime: Duration
        get() = duration.split(":").let { Duration.parse("${it[0]}h ${it[1]}m") }

    val endTime: String
        get() =
            LocalTime.fromSecondOfDay(
                LocalTime.parse(startTime).toSecondOfDay() + durationTime.inWholeSeconds.toInt()
            ).toString()

}
