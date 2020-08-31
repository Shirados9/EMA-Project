package NWUP.com.Alarm.activities

import NWUP.com.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * creates the fragment with a navigator
 * Alarm is based on https://github.com/learntodroid/SimpleAlarmClock/tree/master/app which
 * is programmed in java. We converted it to Kotlin and changed it, so it suits our purposes
 */
class AlarmFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alarm_main, container, false)
    }
}