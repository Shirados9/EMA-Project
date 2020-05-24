package NWUP.com.Timer

import NWUP.com.Timer.util.PrefUtil
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        PrefUtil.setTimerState(TimerFragment.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
