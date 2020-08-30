package NWUP.com.Timer

import NWUP.com.Timer.util.NotificationUtil
import NWUP.com.Timer.util.PrefUtil
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * handles button presses on Notification
 */
class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            AppConstants.ACTION_STOP -> {
                TimerFragment.removeAlarm(context)
                PrefUtil.setTimerState(TimerFragment.TimerState.Stopped, context)
                NotificationUtil.hideTimerNotification(context)
            }
            AppConstants.ACTION_PAUSE -> {
                var secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val alarmSetTime = PrefUtil.getAlarmSetTime(context)
                val nowSeconds = TimerFragment.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PrefUtil.setSecondsRemaining(secondsRemaining, context)

                TimerFragment.removeAlarm(context)
                PrefUtil.setTimerState(TimerFragment.TimerState.Paused, context)
                NotificationUtil.showTimerPaused(context)
            }
            AppConstants.ACTION_RESUME -> {
                val secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val wakeUpTime =
                    TimerFragment.setAlarm(context, TimerFragment.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(TimerFragment.TimerState.Running, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
            AppConstants.ACTION_START -> {
                val secondsRemaining = PrefUtil.getTimerLength(context).toLong()
                val wakeUpTime =
                    TimerFragment.setAlarm(context, TimerFragment.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(TimerFragment.TimerState.Running, context)
                PrefUtil.setSecondsRemaining(secondsRemaining, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
        }
    }
}
