package festival.deandreamatias.com.ui.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import festival.deandreamatias.com.MockSDK
import festival.deandreamatias.com.entity.Show
import festival.deandreamatias.com.ui.getLocalDateTimeNow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

class ShowsViewModel(private val sdk: MockSDK) : ViewModel() {
    private val _state = MutableStateFlow<ShowsUiState>(ShowsUiState.Loading)
    val state: StateFlow<ShowsUiState> = _state

    init {
        loadShows()
    }

    private fun loadShows() {
        viewModelScope.launch {
            _state.emit(ShowsUiState.Loading)
            try {
                val shows = sdk.getShows(forceReload = false)
                _state.value = ShowsUiState.Success(shows, getLocalDateTimeNow())
            } catch (e: Exception) {
                _state.value = ShowsUiState.Error(e.message ?: "Error")
                println("Error: ${e.message}")
            }
        }
    }
}

sealed class ShowsUiState {
    data class Success(
        val shows: List<Show> = emptyList(),
        val currentDateTime: LocalDateTime? = null,
    ) : ShowsUiState()

    data class Error(val error: String = "") : ShowsUiState()
    data object Loading : ShowsUiState()
}
