package festival.deandreamatias.com.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import festival.deandreamatias.com.MockSDK
import festival.deandreamatias.com.entity.Show
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ShowsScreen(
    viewModel: ShowsViewModel = koinViewModel()
) {
    if (viewModel.state.value.isLoading) {
        Text("Loading...")
    }
    if (viewModel.state.value.error.isNotBlank()) {
        Text("Error: ${viewModel.state.value.error}")
    }
    if (viewModel.state.value.shows.isEmpty()) {
        Text("No shows found")
    }
    ShowList(shows = viewModel.state.value.shows)

}

@Composable
fun ShowList(shows: List<Show>) {
    LazyColumn {
        items(shows) { show ->
            ShowItem(show = show)
        }
    }
}

@Composable
fun ShowItem(show: Show) {
    // Display the show details here
    Text(show.name)
}