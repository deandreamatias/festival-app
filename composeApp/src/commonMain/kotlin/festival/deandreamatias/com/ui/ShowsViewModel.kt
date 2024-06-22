package festival.deandreamatias.com.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import festival.deandreamatias.com.MockSDK
import festival.deandreamatias.com.entity.Show
import kotlinx.coroutines.launch

class ShowsViewModel(private val sdk: MockSDK) : ViewModel() {
    private val _state = mutableStateOf(ShowsScreenState())
    val state: State<ShowsScreenState> = _state

    init {
        loadShows()
    }

    fun loadShows() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, shows = emptyList())
            try {
                val launches = sdk.getShows(forceReload = true)
                _state.value = _state.value.copy(isLoading = false, shows = launches)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, shows = emptyList(), error = e.message ?: "")
                println("Error: ${e.message}")
            }
        }
    }
}

data class ShowsScreenState(
    val isLoading: Boolean = false,
    val error: String = "",
    val shows: List<Show> = emptyList()
)