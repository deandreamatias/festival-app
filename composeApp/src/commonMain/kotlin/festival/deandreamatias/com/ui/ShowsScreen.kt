package festival.deandreamatias.com.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(shows) { show ->
            ShowItem(show = show)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowItem(show: Show) {
    Box(
        modifier = Modifier.border(
            BorderStroke(1.dp, Color.DarkGray),
            shape = RoundedCornerShape(8.dp)
        ).padding(8.dp).fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(show.name, fontSize = 20.sp)
                    Text("on stage ${show.stage}")
                }
                Box(modifier = Modifier.height(12.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(show.genre)
                    Text("${show.startTime} to ${show.endTime}")
                }
            }
        }
    }
}