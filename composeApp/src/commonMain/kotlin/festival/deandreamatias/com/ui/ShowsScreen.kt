package festival.deandreamatias.com.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import festival.deandreamatias.com.MockSDK
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
    viewModel.state.value.shows.forEach {
        Text(it.name)
    }
}