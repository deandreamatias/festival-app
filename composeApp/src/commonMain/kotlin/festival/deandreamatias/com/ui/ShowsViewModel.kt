package festival.deandreamatias.com.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import festival.deandreamatias.com.MockSDK
import festival.deandreamatias.com.entity.Show
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
class ShowsViewModel(private val sdk: MockSDK) : ViewModel() {
    private val _state = mutableStateOf(ShowsScreenState())
    val state: State<ShowsScreenState> = _state

    init {
        loadShows()
    }

    private fun loadShows() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, shows = emptyList())
            try {
                val shows = sdk.getShows(forceReload = true)
                /// TODO: Replace this to use the actual current time
                // val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                val now = LocalDateTime(LocalDate(2024, 7, 27), LocalTime(12, 0))

                val indexOfFirstUpcomingShow =
                    shows.indexOfFirst { show -> show.startDateTime > now }
                _state.value = _state.value.copy(
                    isLoading = false,
                    shows = shows,
                    initialId = if (indexOfFirstUpcomingShow >= 0) indexOfFirstUpcomingShow else 0,
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    shows = emptyList(),
                    error = e.message ?: "Undefined error"
                )
                println("Error: ${e.message}")
            }
        }
    }
}

data class ShowsScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val shows: List<Show> = emptyList(),
    val initialId: Int = 0
)