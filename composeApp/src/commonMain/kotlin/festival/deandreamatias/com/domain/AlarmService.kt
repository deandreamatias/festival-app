package festival.deandreamatias.com.domain

import festival.deandreamatias.com.entity.MyTime

interface AlarmService {
    fun setAlarm(time: MyTime)
}