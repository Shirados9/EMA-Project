package NWUP.com.Alarm.alarmsList

import NWUP.com.Alarm.data.Alarm

interface OnToggleAlarmListener {
    fun onToggle(alarm: Alarm?)
}
