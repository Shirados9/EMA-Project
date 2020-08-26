package NWUP.com.Alarm.alarmsList

import NWUP.com.Alarm.data.Alarm
import NWUP.com.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_alarm_list_alarms.view.*
import kotlinx.coroutines.launch


class AlarmsListFragment : Fragment(),
    OnClickAlarmListener {
    private lateinit var alarmRecyclerViewAdapter: AlarmRecyclerViewAdapter
    private lateinit var alarmsListViewModel: AlarmsListViewModel
    private lateinit var alarmsRecyclerView: RecyclerView
    private lateinit var addAlarm: FloatingActionButton
    private lateinit var deletAllButton: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        alarmRecyclerViewAdapter =
            AlarmRecyclerViewAdapter(this)
        alarmsListViewModel = ViewModelProvider(this).get(
            AlarmsListViewModel::class.java
        )
        alarmsListViewModel.alarmsLiveData
            .observe(this,
                Observer<List<Alarm>> { alarms ->
                    if (alarms != null) {
                        alarmRecyclerViewAdapter.setAlarms(alarms as List<Alarm>)
                    }
                })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_alarm_list_alarms, container, false)
        alarmsRecyclerView = view.findViewById(R.id.fragment_listalarms_recylerView)
        alarmsRecyclerView.layoutManager = LinearLayoutManager(context)
        alarmsRecyclerView.adapter = alarmRecyclerViewAdapter



        addAlarm = view.findViewById(R.id.add_alarm)
        deletAllButton = view.delete_alarms

        deletAllButton.setOnClickListener {
            onDeleteAll()
        }
        addAlarm.setOnClickListener{
                Navigation.findNavController(it)
                    .navigate(R.id.action_alarmsListFragment_to_createAlarmFragment)
        }

        return view
    }

    override fun onToggle(alarm: Alarm?) {
        if (alarm != null) {
            if (alarm.started) {
                context?.let { alarm.cancelAlarm(it) }
                alarmsListViewModel.update(alarm)
            } else {
                context?.let { alarm.schedule(it) }
                alarmsListViewModel.update(alarm)
            }
        }
    }

    fun onDeleteAll() {
        lifecycleScope.launch {
            val alarms = alarmsListViewModel.getAlarmData()

            for (alarm in alarms){
                if(alarm.started) {
                    context?.let { alarm.cancelAlarm(it) }
                }
            }
            alarmsListViewModel.deleteAll()
            alarmsRecyclerView.adapter?.notifyDataSetChanged()

        }
    }

    override fun onSwipeDelete(alarm: Alarm?) {

        lifecycleScope.launch {
            val ourAlarms = alarmsListViewModel.getAlarmData()
            for(i in ourAlarms) {
                if(i.alarmId == alarm!!.alarmId) {
                    context?.let { alarm.cancelAlarm(it) }
                }
            }

            alarmsListViewModel.delete(alarm)
            alarmsRecyclerView.adapter?.notifyDataSetChanged()
        }

    }
}