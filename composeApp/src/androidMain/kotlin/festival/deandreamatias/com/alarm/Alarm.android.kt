package festival.deandreamatias.com.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import festival.deandreamatias.com.MainActivity
import festival.deandreamatias.com.domain.AlarmService
import festival.deandreamatias.com.entity.MyTime
import java.util.Calendar

class AlarmServiceAndroid(private val context: Context) : AlarmService {
    private lateinit var alarmManager: AlarmManager

    override fun setAlarm(time: MyTime) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            verifyPermissions()

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent
        )
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun verifyPermissions(): Boolean {
        if (alarmManager.canScheduleExactAlarms()) {
            Log.i("AlarmServiceAndroid", "Can schedule exact alarms")
            return true
        } else {
            Log.i("AlarmServiceAndroid", "Cannot schedule exact alarms. Requesting permission...")
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            context.startActivity(intent)
            return alarmManager.canScheduleExactAlarms()
        }
    }
}
