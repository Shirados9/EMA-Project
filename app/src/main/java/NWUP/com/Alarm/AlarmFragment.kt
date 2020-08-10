package NWUP.com.Alarm

import NWUP.com.R
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_alarm.*
import kotlinx.android.synthetic.main.fragment_alarm.view.*
import kotlinx.android.synthetic.main.fragment_alarm.view.recycler_alarm
import kotlinx.android.synthetic.main.recyclerview_alarm_clock.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class AlarmFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: AlarmRecycleAdapter

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
                //textView.text = SimpleDateFormat("HH:mm").format(cal.time)
                //val Uhrzeit= SimpleDateFormat("HH:mm").format(cal.time)
                //RecyclerItems_Alarm.add(AlarmRecycleItem(Uhrzeit))
            }
            TimePickerDialog(
                context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
            RecyclerItems_Alarm.add(AlarmRecycleItem(SimpleDateFormat("HH:mm").format(cal.time)))
            recycler_alarm.adapter?.notifyItemInserted(RecyclerItems_Alarm.size -1)
        }
    }


    override fun onStart() {
        super.onStart()
        recycler_alarm.layoutManager = LinearLayoutManager(activity)
        recycler_alarm.adapter = AlarmRecycleAdapter(RecyclerItems_Alarm)
        recycler_alarm.setHasFixedSize(true)

    }


}

