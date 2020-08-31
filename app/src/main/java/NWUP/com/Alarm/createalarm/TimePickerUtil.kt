package NWUP.com.Alarm.createalarm

import android.os.Build

import android.widget.TimePicker

/**
 * obtain TimePicker Time with support for older versions
 */
object TimePickerUtil {
    fun getTimePickerHour(tp: TimePicker): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tp.hour
        } else {
            tp.currentHour
        }
    }

    fun getTimePickerMinute(tp: TimePicker): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tp.minute
        } else {
            tp.currentMinute
        }
    }
}