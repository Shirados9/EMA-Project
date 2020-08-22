package NWUP.com.Alarm

import NWUP.com.Alarm.TimePickerUtil.getTimePickerHour
import NWUP.com.Alarm.TimePickerUtil.getTimePickerMinute
import NWUP.com.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_create_alarm.*
import java.util.*


class CreateAlarmFragment : Fragment() {
    private var timePicker: TimePicker = fragment_createalarm_timePicker
    private var title: EditText = fragment_createalarm_title
    private var scheduleAlarm: Button = fragment_createalarm_scheduleAlarm
    private var recurring: CheckBox = fragment_createalarm_recurring
    private var mon: CheckBox = fragment_createalarm_checkMon
    private var tue: CheckBox = fragment_createalarm_checkTue
    private var wed: CheckBox = fragment_createalarm_checkWed
    private var thu: CheckBox = fragment_createalarm_checkThu
    private var fri: CheckBox = fragment_createalarm_checkFri
    private var sat: CheckBox = fragment_createalarm_checkSat
    private var sun: CheckBox = fragment_createalarm_checkSun
    private var recurringOptions: LinearLayout = fragment_createalarm_recurring_options

    private var createAlarmViewModel: CreateAlarmViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAlarmViewModel = ViewModelProviders.of(this).get(
            CreateAlarmViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_create_alarm, container, false)
        recurring.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                recurringOptions.visibility = View.VISIBLE
            } else {
                recurringOptions.visibility = View.GONE
            }
        }
        scheduleAlarm.setOnClickListener {
            fun onClick(v: View?) {
                scheduleAlarm()
                Navigation.findNavController(v!!)
                    .navigate(R.id.action_createAlarmFragment_to_alarmsListFragment)
            }
        }
        return view
    }

    private fun scheduleAlarm() {
        val alarmId: Int = Random().nextInt(Int.MAX_VALUE)
        val alarm = Alarm(
            alarmId,
            getTimePickerHour(timePicker),
            getTimePickerMinute(this.timePicker),
            title.text.toString(),
            true,
            recurring.isChecked,
            mon.isChecked,
            tue.isChecked,
            wed.isChecked,
            thu.isChecked,
            fri.isChecked,
            sat.isChecked,
            sun.isChecked
        )
        createAlarmViewModel?.insert(alarm)
        context?.let { alarm.schedule(it) }
    }
}