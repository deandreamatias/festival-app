package festival.deandreamatias.com.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import festival.deandreamatias.com.MainActivity
import festival.deandreamatias.com.domain.AlarmService
import festival.deandreamatias.com.domain.ScreenNavigator
import festival.deandreamatias.com.entity.MyTime
import java.util.Calendar

class AlarmServiceAndroid(
    private val context: Context,
    private val screenNavigator: ScreenNavigator,
) : AlarmService {
    private var alarmManager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun setAlarm(time: MyTime): Boolean {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("alarmId", time.id)
        Log.i("AlarmServiceAndroid", "setAlarm:id: ${time.id} ")

        val requestCode = time.id
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, time.hour)
            set(Calendar.MINUTE, time.minute)
            set(Calendar.SECOND, 0)
        }

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent
            )
            return true
        } catch (e: Exception) {
            Log.e("AlarmServiceAndroid", "setAlarm: ${e.message}")
            return false
        }
    }

    override fun hasExactAlarmPermission(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) alarmManager.canScheduleExactAlarms() else true

    override fun openExactAlarmPermissionScreen() {
        screenNavigator.openExactAlarmPermissionScreen()
    }

    override fun openAppSettings() {
        screenNavigator.openAppSettings()
    }
}
