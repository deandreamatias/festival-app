package festival.deandreamatias.com.entity

data class MyTime(
    val id: Int,
    val hour: Int,
    val minute: Int,
    var isSet: Boolean = false
) : Comparable<MyTime> {

    override fun compareTo(other: MyTime): Int {
        return if (hour == other.hour) minute - other.minute
        else hour - other.hour
    }
}
