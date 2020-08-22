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
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_create_alarm.*
import java.util.*


class CreateAlarmFragment : Fragment() {
    /*private var timePicker: TimePicker = fragment_createalarm_timePicker
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
*/
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

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_createalarm_recurring.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                fragment_createalarm_recurring_options.visibility = View.VISIBLE
            } else {
                fragment_createalarm_recurring_options.visibility = View.GONE
            }
        }
        fragment_createalarm_scheduleAlarm.setOnClickListener {
                scheduleAlarm()
                Navigation.findNavController(it)
                    .navigate(R.id.action_createAlarmFragment_to_alarmsListFragment)
        }
    }

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