package NWUP.com.Alarm

import NWUP.com.R
import android.app.TimePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_alarm_clock.view.*
import java.text.DateFormat
import java.util.*

class AlarmRecycleAdapter(private val exampleList: List<AlarmRecycleItem>): RecyclerView.Adapter<AlarmRecycleAdapter.ViewHolder>()  {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
        fun changeTimeClick(position: Int)
        fun startAlarm(position: Int)
        fun cancelAlarm(position: Int) {

        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmRecycleAdapter.ViewHolder {
        val createview = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_alarm_clock,parent,false)
        return ViewHolder(createview, mListener)
    }

    override fun getItemCount() = exampleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.Uhrzeit.text = currentItem.Uhrzeit_Alarm



       /* holder.Uhrzeit.setOnClickListener() {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener() { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                cal.set(Calendar.SECOND, 0)



                updateTimeText(cal, holder.Uhrzeit)
                startAlarm(cal)

            }
        }*/


    }
    class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        val Uhrzeit: Button = itemView.Alarm_Uhrzeit
        val switch = itemView.switch_alarm


        init {
            itemView.setOnClickListener() {
                if (listener != null) {
                    var position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }

            Uhrzeit.setOnClickListener() {
                if (listener != null) {
                    var position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.changeTimeClick(position)
                    }
                }
            }

            switch.setOnCheckedChangeListener { buttonView, isChecked ->
                if(listener != null) {
                    var position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        if (isChecked) {
                            listener.startAlarm(position)

                        } else {
                            listener.cancelAlarm(position)

                        }
                    }
                }
            }

                /*
            deleteRow.setOnClickListener() {
                if(listener != null) {
                    var position = adapterPosition
                    if(position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position)
                    }
            }
             */


        }
    }
}

