package NWUP.com.Timer.util

import NWUP.com.Timer.TimerFragment
import android.content.Context
import android.content.SharedPreferences

/**
 * sets and gets values of SharedPreferences for Timer
 */
class PrefUtil {
    companion object {

        private const val SET_TIMER_LENGTH = "com.nwup.timer.set_timer_length"

        fun getTimerLength(context: Context): Int {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(SET_TIMER_LENGTH, Context.MODE_PRIVATE)
            return sharedPreferences.getInt(SET_TIMER_LENGTH, 10)
        }

        fun setTimerLength(seconds: Int, context: Context) {
            val sharedPreferencesEditor =
                context.getSharedPreferences(SET_TIMER_LENGTH, Context.MODE_PRIVATE).edit()
            sharedPreferencesEditor.putInt(SET_TIMER_LENGTH, seconds)
            sharedPreferencesEditor.apply()
        }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID =
            "com.nwup.timer.previous_timer_length_seconds"

        fun getPreviousTimerLengthSeconds(context: Context): Long {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(PREVIOUS_TIMER_LENGTH_SECONDS_ID, Context.MODE_PRIVATE)
            return sharedPreferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimerLengthSeconds(seconds: Long, context: Context) {
            val sharedPreferencesEditor =
                context.getSharedPreferences(PREVIOUS_TIMER_LENGTH_SECONDS_ID, Context.MODE_PRIVATE)
                    .edit()
            sharedPreferencesEditor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            sharedPreferencesEditor.apply()
        }

        private const val TIMER_STATE_ID = "com.nwup.timer.timer_state"

        fun getTimerState(context: Context): TimerFragment.TimerState {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(TIMER_STATE_ID, Context.MODE_PRIVATE)
            val ordinal = sharedPreferences.getInt(TIMER_STATE_ID, 0)
            return TimerFragment.TimerState.values()[ordinal]
        }

        fun setTimerState(state: TimerFragment.TimerState, context: Context) {
            val sharedPreferencesEditor =
                context.getSharedPreferences(TIMER_STATE_ID, Context.MODE_PRIVATE).edit()
            val ordinal = state.ordinal
            sharedPreferencesEditor.putInt(TIMER_STATE_ID, ordinal)
            sharedPreferencesEditor.apply()
        }

        private const val SECONDS_REMAINING_ID = "com.nwup.timer.seconds_remaining"

        fun getSecondsRemaining(context: Context): Long {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(SECONDS_REMAINING_ID, Context.MODE_PRIVATE)
            return sharedPreferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(seconds: Long, context: Context) {
            val sharedPreferencesEditor =
                context.getSharedPreferences(SECONDS_REMAINING_ID, Context.MODE_PRIVATE).edit()
            sharedPreferencesEditor.putLong(SECONDS_REMAINING_ID, seconds)
            sharedPreferencesEditor.apply()
        }

        private const val ALARM_SET_TIME_ID = "com.nwup.timer.backgrounded_time"

        fun getAlarmSetTime(context: Context): Long {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(ALARM_SET_TIME_ID, Context.MODE_PRIVATE)
            return sharedPreferences.getLong(ALARM_SET_TIME_ID, 0)
        }

        fun setAlarmSetTime(time: Long, context: Context) {
            val sharedPreferencesEditor =
                context.getSharedPreferences(ALARM_SET_TIME_ID, Context.MODE_PRIVATE).edit()
            sharedPreferencesEditor.putLong(ALARM_SET_TIME_ID, time)
            sharedPreferencesEditor.apply()
        }
    }
}