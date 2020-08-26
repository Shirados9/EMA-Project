package NWUP.com.Alarm.alarmsList

import NWUP.com.Alarm.data.Alarm
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_alarm_item.view.*


class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val alarmTime: TextView = itemView.item_alarm_time
    private val alarmRecurring: ImageView = itemView.item_alarm_recurring
    private val alarmRecurringDays: TextView = itemView.item_alarm_recurringDays
    private val alarmTitle: TextView = itemView.item_alarm_title
    private val alarmStarted: Switch = itemView.item_alarm_started
    private val alarmDelete: Button = itemView.item_alarm_delete
    fun bind(alarm: Alarm, listener: OnClickAlarmListener) {
        val alarmText = String.format("%02d:%02d", alarm.hour, alarm.minute)
        alarmTime.text = alarmText
        alarmStarted.isChecked = alarm.started
        if (alarm.recurring) {
            //alarmRecurring.setImageResource(R.drawable.ic_repeat_black_24dp)
            alarmRecurringDays.text = alarm.getRecurringDaysText().toString()
        } else {
            //alarmRecurring.setImageResource(R.drawable.ic_looks_one_black_24dp)
            alarmRecurringDays.text = "Once Off"
        }
        if (alarm.title.isNotEmpty()) {
            alarmTitle.text = alarm.title
        } else {
            alarmTitle.text = "My alarm"
        }
        alarmStarted.setOnCheckedChangeListener { buttonView, isChecked -> listener.onToggle(alarm) }
        alarmDelete.setOnClickListener {listener.onSwipeDelete(alarm = alarm)}


    }


}