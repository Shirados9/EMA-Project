package NWUP.com.Alarm.alarmsList

import NWUP.com.Alarm.data.Alarm
import NWUP.com.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for AlarmListFragment
 */
class AlarmRecyclerViewAdapter(listener: OnClickAlarmListener) :
    RecyclerView.Adapter<AlarmViewHolder>() {

    private var alarms: List<Alarm>
    private val listener: OnClickAlarmListener

    init {
        alarms = ArrayList()
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_alarm_item, parent, false)
        return AlarmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarm = alarms[position]
        holder.bind(alarm, listener)
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    fun setAlarms(alarms: List<Alarm>) {
        this.alarms = alarms
        notifyDataSetChanged()
    }
}