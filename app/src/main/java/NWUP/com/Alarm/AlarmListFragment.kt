package NWUP.com.Alarm

import NWUP.com.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AlarmsListFragment : Fragment(){
    private lateinit var alarmRecyclerViewAdapter: AlarmRecyclerViewAdapter
    private lateinit var alarmsListViewModel: AlarmsListViewModel
    private lateinit var alarmsRecyclerView: RecyclerView
    private lateinit var addAlarm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        alarmRecyclerViewAdapter = AlarmRecyclerViewAdapter()
        alarmsListViewModel = ViewModelProviders.of(this).get(
            AlarmsListViewModel::class.java
        )
        alarmsListViewModel.alarmsLiveData
            .observe(this, object : Observer<in List<Alarm>!> {
                fun onChanged(alarms: List<Alarm?>?) {
                    if (alarms != null) {
                        alarmRecyclerViewAdapter.setAlarms(alarms as List<Alarm>)
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_listalarms, container, false)
        alarmsRecyclerView = view.findViewById(R.id.fragment_listalarms_recylerView)
        alarmsRecyclerView.layoutManager = LinearLayoutManager(context)
        alarmsRecyclerView.adapter = alarmRecyclerViewAdapter
        addAlarm = view.findViewById(R.id.fragment_listalarms_addAlarm)
        addAlarm.setOnClickListener{
            fun onClick(v: View?) {
                Navigation.findNavController(v)
                    .navigate(R.id.action_alarmsListFragment_to_createAlarmFragment)
            }
        }
        return view
    }

    fun onToggle(alarm: Alarm) {
        if (alarm.started) {
            context?.let { alarm.cancelAlarm(it) }
            alarmsListViewModel.update(alarm)
        } else {
            context?.let { alarm.schedule(it) }
            alarmsListViewModel.update(alarm)
        }
    }
}