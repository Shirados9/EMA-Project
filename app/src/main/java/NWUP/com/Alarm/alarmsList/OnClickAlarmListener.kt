package NWUP.com.Alarm.alarmsList

import NWUP.com.Alarm.data.Alarm

interface OnClickAlarmListener {
    fun onToggle(alarm: Alarm?)
    fun onSwipeDelete(alarm: Alarm?)

}
