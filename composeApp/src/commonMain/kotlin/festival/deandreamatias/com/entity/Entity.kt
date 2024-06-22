package festival.deandreamatias.com.entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
}
