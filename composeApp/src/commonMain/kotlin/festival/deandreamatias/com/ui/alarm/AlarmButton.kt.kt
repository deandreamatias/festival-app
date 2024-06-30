package festival.deandreamatias.com.ui.alarm

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import festival.deandreamatias.com.entity.MyTime
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AlarmButton(time: MyTime, viewModel: AlarmViewModel = koinViewModel(key = time.id.toString())) {
    Button(onClick = {
        viewModel.verifyPermission(time)
    }, modifier = Modifier.padding(8.dp)) {
        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Add alarm")
    }
    PermissionDialog(viewModel)
}


@Composable
fun PermissionDialog(viewModel: AlarmViewModel) {
    if (viewModel.state.value.shouldShowDialog) {
        AlertDialog(
            onDismissRequest = {
                viewModel.closeDialogPermission()
            },
            title = { Text(text = "Alarm permission") },
            text = { Text(text = "Please enable permission to can create exact alarm. After that, try to add a alarm again") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.openAlarmPermissionSettings()
                    }
                ) {
                    Text(text = "Open permission settings")
                }
            }
        )
    }
}