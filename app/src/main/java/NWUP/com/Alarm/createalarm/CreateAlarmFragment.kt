package NWUP.com.Alarm.createalarm

import NWUP.com.Alarm.createalarm.TimePickerUtil.getTimePickerHour
import NWUP.com.Alarm.createalarm.TimePickerUtil.getTimePickerMinute
import NWUP.com.Alarm.data.Alarm
import NWUP.com.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_alarm_create_alarm.*
import java.util.*

/**
 * creates the fragment to create alarms and save it in the database
 */
class CreateAlarmFragment : Fragment() {
    private var createAlarmViewModel: CreateAlarmViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAlarmViewModel = ViewModelProvider(this).get(
            CreateAlarmViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_alarm_create_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_createalarm_timePicker.setIs24HourView(true)

        fragment_createalarm_recurring.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                fragment_createalarm_recurring_options.visibility = View.VISIBLE
            } else {
                fragment_createalarm_recurring_options.visibility = View.GONE
            }
        }

        //sets current fragment to AlarmListFragment
        fragment_createalarm_scheduleAlarm.setOnClickListener {
            scheduleAlarm()
            Navigation.findNavController(it)
                .navigate(R.id.action_createAlarmFragment_to_alarmsListFragment)
        }
    }

    /**
     * creates alarmItem to input in database
     */
    private fun scheduleAlarm() {
        val alarmId: Int = Random().nextInt(Int.MAX_VALUE)
        val alarm = Alarm(
            alarmId,
            getTimePickerHour(fragment_createalarm_timePicker),
            getTimePickerMinute(fragment_createalarm_timePicker),
            fragment_createalarm_title.text.toString(),
            true,
            fragment_createalarm_recurring.isChecked,
            fragment_createalarm_checkMon.isChecked,
            fragment_createalarm_checkTue.isChecked,
            fragment_createalarm_checkWed.isChecked,
            fragment_createalarm_checkThu.isChecked,
            fragment_createalarm_checkFri.isChecked,
            fragment_createalarm_checkSat.isChecked,
            fragment_createalarm_checkSun.isChecked
        )
        createAlarmViewModel?.insert(alarm)
        context?.let { alarm.schedule(it) }
    }
}