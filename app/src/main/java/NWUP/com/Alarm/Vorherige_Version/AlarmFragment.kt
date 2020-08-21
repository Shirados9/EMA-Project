package NWUP.com.Alarm.Vorherige_Version

import NWUP.com.Alarm.AlertReceiver
import NWUP.com.R
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_alarm.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class AlarmFragment : Fragment() {
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: AlarmRecycleAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var alarmManager: AlarmManager

    private lateinit var Alarm: Button

    companion object {
        var RecyclerItems_Alarm = ArrayList<AlarmRecycleItem>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binder = inflater.inflate(R.layout.fragment_alarm, container, false)


        return binder;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_alarm.setOnClickListener() {

            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                RecyclerItems_Alarm.add(
                    AlarmRecycleItem(
                        SimpleDateFormat("HH:mm").format(cal.time)
                    )
                )
                adapter.notifyItemInserted(RecyclerItems_Alarm.size-1)
            }
            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        //buttonRemove.setOnClickListener() {}



    }

    public fun removeItem(position: Int) {

    }



    override fun onStart() {
        super.onStart()
        buildRecyclerView()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    //hourOfDay:Int, minute:Int
    fun startAlarm_frag(c: Calendar, position: Int) {

        alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlertReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, position, intent, 0)

        if(c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1)
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)




    }

    fun cancelAlarm_frag(position: Int) {
        alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlertReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, position, intent, 0)
        alarmManager!!.cancel(pendingIntent)

    }


    private fun buildRecyclerView() {
        adapter =
            AlarmRecycleAdapter(
                RecyclerItems_Alarm
            )




        recycler_alarm.layoutManager = LinearLayoutManager(activity)
        recycler_alarm.adapter = adapter
        recycler_alarm.setHasFixedSize(true)








        adapter.setOnItemClickListener(object :
            AlarmRecycleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                recycler_alarm.get(position)
            }

            override fun onDeleteClick(position: Int) {
                removeItem(position)
            }

            override fun changeTimeClick(position: Int) {
                val cal = Calendar.getInstance()
                val timeSetListener = TimePickerDialog.OnTimeSetListener() { timePicker, hour, minute ->
                    cal.set(Calendar.HOUR_OF_DAY, hour)
                    cal.set(Calendar.MINUTE, minute)
                    val text = SimpleDateFormat("HH:mm").format(cal.time)
                    RecyclerItems_Alarm.get(position).changeUhrzeit(text)
                    recycler_alarm.adapter?.notifyItemChanged(position)
                }
                TimePickerDialog(
                    context,
                    timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
                ).show()
            }

            override fun startAlarm(position: Int) {
                startAlarm_frag(RecyclerItems_Alarm.get(position).getSetTime(), position)
            }

            override fun cancelAlarm(position: Int) {
                cancelAlarm_frag(position)
            }
        })
    }


}

