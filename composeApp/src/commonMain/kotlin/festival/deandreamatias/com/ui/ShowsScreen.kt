package festival.deandreamatias.com.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import festival.deandreamatias.com.entity.Show
import festival.deandreamatias.com.ui.alarm.AlarmButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun ShowsScreen(
    viewModel: ShowsViewModel = koinViewModel()
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Festival App") }) },
        content = { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                if (viewModel.state.value.isLoading) {
                    CircularProgressIndicator()
                    return@Surface
                }
                if (viewModel.state.value.error.isNotBlank()) {
                    Text("Error: ${viewModel.state.value.error}")
                    return@Surface
                }
                if (viewModel.state.value.shows.isEmpty()) {
                    Text("No shows found")
                }
                ShowList(
                    shows = viewModel.state.value.shows,
                    currentDateTime = viewModel.state.value.currentDateTime,
                )
            }
        }
    )
}

@Composable
fun ShowList(
    shows: List<Show>,
    currentDateTime: LocalDateTime?
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var index = 0

    val items = mutableListOf<Any>()
    shows.groupBy { it.localDate }.forEach { (date, showsForDate) ->
        items.add(date)
        items.addAll(showsForDate)
    }
    if (currentDateTime != null) {
        index = items.indexOfFirst { it is Show && it.startDateTime > currentDateTime }
        coroutineScope.launch {
            if (index != -1) listState.animateScrollToItem(index)
        }
    }

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            when (item) {
                is LocalDate -> Text(item.getRelativeDay())
                is Show -> ShowItem(
                    show = item,
                    isNextShow = item.id == items[index].let { it as? Show }?.id
                )
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowItem(show: Show, isNextShow: Boolean = false) {
    val blinkState = remember { mutableStateOf(false) }
    val color: Color by animateColorAsState(if (blinkState.value) Color.Gray else Color.White)

    LaunchedEffect(key1 = isNextShow) {
        if (isNextShow) {
            blinkState.value = true
            delay(500)
            blinkState.value = false
            delay(500)
            blinkState.value = true
            delay(500)
            blinkState.value = false
        }
    }
    Box(
        modifier = Modifier.border(
            BorderStroke(1.dp, Color.DarkGray),
            shape = RoundedCornerShape(8.dp)
        ).padding(8.dp).fillMaxWidth().background(color)
    ) {
        FlowRow(
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
            AlarmButton(time = show.timeAlarm)
        }
    }
}
