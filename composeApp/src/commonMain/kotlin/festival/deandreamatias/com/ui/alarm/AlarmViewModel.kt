package festival.deandreamatias.com.ui.alarm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import festival.deandreamatias.com.domain.AlarmService
import festival.deandreamatias.com.entity.MyTime
import kotlinx.coroutines.launch

class AlarmViewModel(private val alarmService: AlarmService) : ViewModel() {
    private val _state = mutableStateOf(AlarmState())
    val state: State<AlarmState> = _state

    fun verifyPermission(time: MyTime) {
        viewModelScope.launch {
            if (alarmService.hasExactAlarmPermission()) {
                addAlarm(time)
                return@launch
            }
            showDialogPermission()
        }
    }

    private fun addAlarm(time: MyTime) {
        if (alarmService.hasExactAlarmPermission()) alarmService.setAlarm(time)
    }

    private fun showDialogPermission() {
        _state.value = _state.value.copy(shouldShowDialog = true)
    }

    fun openAlarmPermissionSettings() {
        viewModelScope.launch {
            alarmService.openExactAlarmPermissionScreen()
            closeDialogPermission()
        }
    }

    fun closeDialogPermission() {
        _state.value = _state.value.copy(shouldShowDialog = false)
    }
}

data class AlarmState(
    val shouldShowDialog: Boolean = false,
)
