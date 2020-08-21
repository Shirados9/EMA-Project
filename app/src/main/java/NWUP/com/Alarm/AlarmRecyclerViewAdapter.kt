package NWUP.com.Alarm

import NWUP.com.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class AlarmRecyclerViewAdapter(listener: OnToggleAlarmListener) :
    RecyclerView.Adapter<AlarmViewHolder>() {
    private var alarms: List<Alarm>
    private val listener: OnToggleAlarmListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
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

    init {
        alarms = ArrayList()
        this.listener = listener
    }
}